import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.paypay.exchangerates.R
import com.paypay.exchangerates.domain.CurrencyRate
import com.paypay.exchangerates.presentation.Dimens
import com.paypay.exchangerates.presentation.components.DropDownMenus
import com.paypay.exchangerates.presentation.components.GridView
import com.paypay.exchangerates.presentation.components.NumericTextField
import com.paypay.exchangerates.presentation.home.HomeViewModel

@Composable
internal fun HomeDataContent(
    items: List<CurrencyRate>,
    viewModel: HomeViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .testTag("DataContent")
            .padding(bottom = Dimens.Dp20)
    ) {
        val (rateText, btnReset, dropDown, gridView) = createRefs()

        // State to hold the selected currency
        var mSelectedCurrency by remember {
            mutableStateOf(items.firstOrNull { it.currency == "USD" } ?: CurrencyRate("USD", 1.0))
        }

        // Numeric Text Field for entering amount
        NumericTextField(
            value = mSelectedCurrency.currencyRate.toString(),
            label = stringResource(R.string.enter_amount),
            modifier = Modifier
                .constrainAs(rateText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = Dimens.Dp20,
                    start = Dimens.Dp15,
                    end = Dimens.Dp15,
                    bottom = Dimens.Dp10
                )
                .fillMaxWidth()
                .testTag("NumericTextField"),
            onValueChange = {
                mSelectedCurrency = mSelectedCurrency.copy(currencyRate = it.toDouble())
            },
            onDoneChange = {
                viewModel.fetchData(mSelectedCurrency)
            }
        )

        Button(
            onClick = { viewModel.fetchData() },
            modifier = Modifier
                .wrapContentSize()
                .padding(start = Dimens.Dp15)
                .constrainAs(btnReset) {
                    top.linkTo(rateText.bottom)
                    start.linkTo(rateText.start)
                    bottom.linkTo(dropDown.bottom)
                }
        ) {
            Text(text = "Reset Base USD")
        }

        // Dropdown menu for selecting currency
        DropDownMenus(
            Dimens.DropDownWidth,
            modifier = Modifier
                .constrainAs(dropDown) {
                    top.linkTo(rateText.bottom)
                    end.linkTo(rateText.end)
                }
                .padding(start = Dimens.Dp15, end = Dimens.Dp15, bottom = Dimens.Dp10)
                .testTag("DropDownMenus"),
            items = items,
            mSelectedCurrency = mSelectedCurrency
        ) {
            mSelectedCurrency = it
        }


        // Grid view to display currency rates
        GridView(
            modifier = Modifier
                .fillMaxSize()
                .testTag("GridView")
                .padding(bottom = Dimens.MediumPadding4)
                .constrainAs(gridView) {
                    top.linkTo(dropDown.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            items = items
        )
    }
}
