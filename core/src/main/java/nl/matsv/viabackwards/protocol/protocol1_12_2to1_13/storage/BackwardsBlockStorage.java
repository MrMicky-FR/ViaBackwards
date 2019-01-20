/*
 * Copyright (c) 2016 Matsv
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.matsv.viabackwards.protocol.protocol1_12_2to1_13.storage;

import com.google.common.collect.Sets;
import us.myles.ViaVersion.api.data.StoredObject;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.api.minecraft.Position;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BackwardsBlockStorage extends StoredObject {
    // This BlockStorage is very exclusive (;
    private static final Set<Integer> whitelist = Sets.newConcurrentHashSet();

    static {
        // Flower pots
//        whitelist.add(5266);

        // Add those beds
        for (int i = 0; i < (16 * 16); i++)
            whitelist.add(748 + i);

        // Add the white banners
//        for (int i = 0; i < 20; i++)
//            whitelist.add(6854 + i);
//
        // Add the white wall banners
//        for (int i = 0; i < 4; i++) {
//            whitelist.add(7110 + i);
//        }

        // Skeleton skulls
//        for (int i = 0; i < 5; i++)
//            whitelist.add(5447 + i);
    }

    private Map<Position, Integer> blocks = new ConcurrentHashMap<>();

    public BackwardsBlockStorage(UserConnection user) {
        super(user);
    }

    public void checkAndStore(Position position, int block) {
        if (!whitelist.contains(block)) {
            // Remove if not whitelisted
            if (blocks.containsKey(position))
                blocks.remove(position);
            return;
        }

        blocks.put(position, block);
    }

    public boolean isWelcome(int block) {
        return whitelist.contains(block);
    }

    public boolean contains(Position position) {
        return blocks.containsKey(position);
    }

    public int get(Position position) {
        return blocks.get(position);
    }

    public int remove(Position position) {
        return blocks.remove(position);
    }

}
