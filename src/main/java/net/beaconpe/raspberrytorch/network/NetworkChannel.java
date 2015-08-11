/**
 * This file is part of RaspberryTorch.
 *
 * RaspberryTorch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RaspberryTorch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RaspberryTorch.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.beaconpe.raspberrytorch.network;

/**
 * Represents a RakNet Network Channel.
 *
 * @author jython234
 */
public enum NetworkChannel {
    CHANNEL_NONE(0),
    CHANNEL_PRIORITY(1),
    CHANNEL_WORLD_CHUNKS(2),
    CHANNEL_MOVEMENT(3),
    CHANNEL_BLOCKS(4),
    CHANNEL_WORLD_EVENTS(5),
    CHANNEL_ENTITY_SPAWNING(6),
    CHANNEL_TEXT(7),
    CHANNEL_END(31);

    private byte channel;

    private NetworkChannel(byte channel){
        this.channel = channel;
    }

    private NetworkChannel(int channel) {
        this.channel = (byte) channel;
    }

    public byte getAsByte(){
        return channel;
    }
}
