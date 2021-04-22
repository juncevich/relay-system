package com.relay.commentsservice.web.dto

import java.util.*

data class RelayComment(val id: UUID, val relayId: UUID, val text: String)
