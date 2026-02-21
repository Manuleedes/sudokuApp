package com.lidigu.sudoku.persistence

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object GameSettingsSerializer : Serializer<GameSettings> {
    override val defaultValue: GameSettings = GameSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GameSettings {
        try {
            return GameSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: GameSettings, output: OutputStream) = t.writeTo(output)
}
