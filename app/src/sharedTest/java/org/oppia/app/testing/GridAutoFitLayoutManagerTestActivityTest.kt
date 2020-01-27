package org.oppia.app.testing

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.oppia.app.R
import org.oppia.app.recyclerview.RecyclerViewMatcher.Companion.hasGridItemCount
import org.oppia.app.utility.OrientationChangeAction.Companion.orientationLandscape
import org.oppia.app.utility.OrientationChangeAction.Companion.orientationPortrait

@RunWith(AndroidJUnit4::class)
class GridAutoFitLayoutManagerTestActivityTest {

  @Before
  @ExperimentalCoroutinesApi
  fun setUp() {
    Intents.init()
  }

  @After
  fun tearDown() {
    Intents.release()
  }

  @Test
  fun testGridAutoFitLayoutManagerTestActivity_checkSpanCountOnPortrait_spanCountTwoVerifiedSuccessfully() {
    launchGridAutoFitLayoutManagerTestActivityIntent(320, 160).use {
      onView(ViewMatchers.isRoot()).perform(orientationPortrait())
      onView(withId(R.id.grid_recycler_view)).check(hasGridItemCount(2))
    }
  }

  @Test
  fun testGridAutoFitLayoutManagerTestActivity_checkSpanCountOnPortrait_spanCountThreeVerifiedSuccessfully() {
    launchGridAutoFitLayoutManagerTestActivityIntent(300, 100).use {
      onView(ViewMatchers.isRoot()).perform(orientationPortrait())
      onView(withId(R.id.grid_recycler_view)).check(hasGridItemCount(3))
    }
  }

  @Test
  fun testGridAutoFitLayoutManagerTestActivity_configurationChange_checkSpanCount_spanCountThreeVerifiedSuccessfully() {
    launchGridAutoFitLayoutManagerTestActivityIntent(480, 160).use {
      onView(ViewMatchers.isRoot()).perform(orientationLandscape())
      onView(withId(R.id.grid_recycler_view)).check(hasGridItemCount(3))
    }
  }

  @Test
  fun testGridAutoFitLayoutManagerTestActivity_configurationChange_checkSpanCount_spanCountFourVerifiedSuccessfully() {
    launchGridAutoFitLayoutManagerTestActivityIntent(480, 120).use {
      onView(ViewMatchers.isRoot()).perform(orientationLandscape())
      onView(withId(R.id.grid_recycler_view)).check(hasGridItemCount(4))
    }
  }

  private fun launchGridAutoFitLayoutManagerTestActivityIntent(
    recyclerViewWidth: Int,
    columnWidth: Int
  ): ActivityScenario<GridAutoFitLayoutManagerTestActivity> {
    val intent = GridAutoFitLayoutManagerTestActivity.createGridAutoFitLayoutManagerTestActivityIntent(
      ApplicationProvider.getApplicationContext(),
      recyclerViewWidth, columnWidth
    )
    return launch(intent)
  }
}