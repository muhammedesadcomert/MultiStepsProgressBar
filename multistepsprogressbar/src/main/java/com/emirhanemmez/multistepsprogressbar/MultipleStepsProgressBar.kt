package com.emirhanemmez.multistepsprogressbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emirhanemmez.multistepsprogressbar.model.ProgressProperties
import com.emirhanemmez.multistepsprogressbar.model.StepData
import com.emirhanemmez.multistepsprogressbar.model.StepId
import com.emirhanemmez.multistepsprogressbar.model.StepProperties

@Composable
fun MultipleStepsProgressBar(
    modifier: Modifier = Modifier,
    stepDataList: List<StepData>,
    stepProperties: StepProperties = StepProperties.getDefault()
) {
    Row(
        modifier = modifier
    ) {
        stepDataList.forEachIndexed { index, stepData ->
            Row(modifier = Modifier.weight(1f)) {
                StepItem(
                    modifier = Modifier.weight(1f),
                    stepData = stepData,
                    stepProperties = stepProperties
                )

                if (index != stepDataList.lastIndex) {
                    Spacer(modifier = Modifier.width(stepProperties.marginBetweenSteps))
                }
            }
        }
    }
}

@Composable
fun StepItem(
    modifier: Modifier = Modifier,
    stepData: StepData,
    stepProperties: StepProperties,
) {
    Column(modifier = modifier) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(stepProperties.progressProperties.progressCornerRadius))
                .height(5.dp),
            progress = stepData.progress,
            trackColor = stepProperties.progressProperties.progressBackgroundColor,
            color = stepProperties.progressProperties.progressColor
        )

        Spacer(modifier = Modifier.height(stepProperties.marginBetweenTextAndProgress))

        Text(
            text = stepData.stepName,
            style = stepProperties.textProperties.textStyle,
            color = if (stepData.progress == 0f)
                stepProperties.textProperties.inActiveTextColor
            else
                stepProperties.textProperties.activeTextColor
        )
    }
}

@Preview
@Composable
fun MultipleStepsProgressBarPreview() {
    MaterialTheme {
        Surface {
            MultipleStepsProgressBar(
                stepDataList = listOf(
                    StepData(
                        stepId = StepId(0),
                        stepName = "Step1",
                        progress = ProgressProperties.PROGRESS_FULL
                    ),
                    StepData(
                        stepId = StepId(1),
                        stepName = "Step2",
                        progress = ProgressProperties.PROGRESS_NONE,
                    ),
                    StepData(
                        stepId = StepId(2),
                        stepName = "Step3",
                        progress = ProgressProperties.PROGRESS_HALF
                    )
                )
            )
        }
    }
}
