package net.beaconpe.raspberrytorch.network;

/**
 * Network Constants class.
 *
 * @author jython234
 */
public abstract class NetworkIds {
    /**
     * The protocol of Minecraft: Pi Edition that this server implements.
     */
    public final static int PROTOCOL = 9;

    public final static byte LOGIN_PACKET = (byte) 0x82;
    public final static byte LOGIN_STATUS_PACKET = (byte) 0x83;
    public final static byte READY_PACKET = (byte) 0x84;
    public final static byte MESSAGE_PACKET = (byte) 0x85;
    public final static byte SET_TIME_PACKET = (byte) 0x86;
    public final static byte START_GAME_PACKET = (byte) 0x87;
    public final static byte ADD_MOB_PACKET = (byte) 0x88;
    public final static byte ADD_PLAYER_PACKET = (byte) 0x89;
    public final static byte REMOVE_PLAYER_PACKET = (byte) 0x8a;
    @Deprecated
    public final static byte ADD_ENTITY_PACKET = (byte) 0x8c;
    public final static byte REMOVE_ENTITY_PACKET = (byte) 0x8d;
    public final static byte ADD_ITEM_ENTITY_PACKET = (byte) 0x8e;
    public final static byte TAKE_ITEM_ENTITY_PACKET = (byte) 0x8f;
    public final static byte MOVE_ENTITY_PACKET = (byte) 0x90;
    public final static byte MOVE_ENTITY_POSROT_PACKET = (byte) 0x93;
    public final static byte MOVE_PLAYER_PACKET = (byte) 0x94;
    public final static byte PLACE_BLOCK_PACKET = (byte) 0x95;
    public final static byte REMOVE_BLOCK_PACKET = (byte) 0x96;
    public final static byte UPDATE_BLOCK_PACKET = (byte) 0x97;
    public final static byte ADD_PAINTING_PACKET = (byte) 0x98;
    public final static byte EXPLODE_PACKET = (byte) 0x99;
    public final static byte LEVEL_EVENT_PACKET = (byte) 0x9a;
    public final static byte TILE_EVENT_PACKET = (byte) 0x9b;
    public final static byte ENTITY_EVENT_PACKET = (byte) 0x9c;
    public final static byte REQUEST_CHUNK_PACKET = (byte) 0x9d;
    public final static byte CHUNK_DATA_PACKET = (byte) 0x9e;
    public final static byte PLAYER_EQUIPMENT_PACKET = (byte) 0x9f;
    public final static byte PLAYER_ARMOR_EQUIPMENT_PACKET = (byte) 0xa0;
    public final static byte INTERACT_PACKET = (byte) 0xa1;
    public final static byte USE_ITEM_PACKET = (byte) 0xa2;
    public final static byte PLAYER_ACTION_PACKET = (byte) 0xa3;
    public final static byte HURT_ARMOR_PACKET = (byte) 0xa5;
    public final static byte SET_ENTITY_DATA_PACKET = (byte) 0xa6;
    public final static byte SET_ENTITY_MOTION_PACKET = (byte) 0xa7;
    public final static byte SET_HEALTH_PACKET = (byte) 0xa8;
    public final static byte SET_SPAWN_POSITION_PACKET = (byte) 0xa9;
    public final static byte ANIMATE_PACKET = (byte) 0xaa;
    public final static byte RESPAWN_PACKET = (byte) 0xab;
    public final static byte SEND_INVENTORY_PACKET = (byte) 0xac;
    public final static byte DROP_ITEM_PACKET = (byte) 0xad;
    public final static byte CONTAINER_OPEN_PACKET = (byte) 0xae;
    public final static byte CONTAINER_CLOSE_PACKET = (byte) 0xaf;
    public final static byte CONTAINER_SET_SLOT_PACKET = (byte) 0xb0;
    public final static byte CONTAINER_SET_DATA_PACKET = (byte) 0xb1;
    public final static byte CONTAINER_SET_CONTENT_PACKET = (byte) 0xb2;
    public final static byte CONTAINER_ACK_PACKET = (byte) 0xb3;
    @Deprecated
    public final static byte CHAT_PACKET = (byte) 0xb4;
    public final static byte SIGN_UPDATE_PACKET = (byte) 0xb5;
    public final static byte ADVENTURE_SETTINGS_PACKET = (byte) 0xb6;
}
