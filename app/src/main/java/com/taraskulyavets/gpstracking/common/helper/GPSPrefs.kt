package com.taraskulyavets.gpstracking.common.helper

import com.taraskulyavets.gpstracking.login.model.User


class GPSPrefs()  {
    var user: User? by PrefsDelegate("user", null, User::class)
    var token: String by PrefsDelegate("token", "")
}