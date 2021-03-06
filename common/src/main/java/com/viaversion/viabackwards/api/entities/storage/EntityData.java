/*
 * This file is part of ViaBackwards - https://github.com/ViaVersion/ViaBackwards
 * Copyright (C) 2016-2021 ViaVersion and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.viaversion.viabackwards.api.entities.storage;

import com.viaversion.viaversion.protocols.protocol1_13to1_12_2.ChatRewriter;
import org.checkerframework.checker.nullness.qual.Nullable;

public class EntityData {
    private final int id;
    private final int replacementId;
    private Object mobName;
    private MetaCreator defaultMeta;

    public EntityData(int id, int replacementId) {
        this.id = id;
        this.replacementId = replacementId;
    }

    public EntityData jsonName(String name) {
        this.mobName = ChatRewriter.legacyTextToJson(name);
        return this;
    }

    public EntityData mobName(String name) {
        this.mobName = name;
        return this;
    }

    public EntityData spawnMetadata(MetaCreator handler) {
        this.defaultMeta = handler;
        return this;
    }

    public boolean hasBaseMeta() {
        return this.defaultMeta != null;
    }

    public int typeId() {
        return id;
    }

    /**
     * @return custom mobname, can be either a String or a JsonElement
     */
    public @Nullable Object mobName() {
        return mobName;
    }

    public int replacementId() {
        return replacementId;
    }

    public @Nullable MetaCreator defaultMeta() {
        return defaultMeta;
    }

    public boolean isObjectType() {
        return false;
    }

    public int objectData() {
        return -1;
    }

    @Override
    public String toString() {
        return "EntityData{" +
                "id=" + id +
                ", mobName='" + mobName + '\'' +
                ", replacementId=" + replacementId +
                ", defaultMeta=" + defaultMeta +
                '}';
    }

    @FunctionalInterface
    public interface MetaCreator {

        void createMeta(WrappedMetadata storage);
    }
}
