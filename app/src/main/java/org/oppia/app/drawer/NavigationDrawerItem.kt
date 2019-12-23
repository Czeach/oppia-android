package org.oppia.app.drawer

import org.oppia.app.R

/** Enum Represents different destinations that can be selected by the user from the navigation drawer. */
enum class NavigationDrawerItem(val value: Int) {
  HOME(R.id.nav_home), HELP(R.id.nav_help);

  companion object {
    fun valueFromNavId(id: Int): NavigationDrawerItem {
      for (item: NavigationDrawerItem in values()) {
        if (item.value.equals(id)) return item
      }
      return throw  IllegalArgumentException("NavigationDrawerItem not found by " + id)
    }
  }
}
