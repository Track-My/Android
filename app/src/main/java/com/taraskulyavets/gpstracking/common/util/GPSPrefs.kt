package com.taraskulyavets.gpstracking.common.util

import com.taraskulyavets.gpstracking.login.model.User


class GPSPrefs()  {
    var user: User? by PrefsDelegate("user", null, User::class)
    var token: String by PrefsDelegate("token", "")
    var uuid: String by PrefsDelegate("UUID", "")
}