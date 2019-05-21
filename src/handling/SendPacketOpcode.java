package handling;

import constants.GameConstants;
import static handling.SendPacketOpcode.values;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public enum SendPacketOpcode
        implements WritableIntValueHolder {

    
    // General
    PING,
    AUTH_RESPONSE,
    // Login
    LOGIN_STATUS,
    SEND_LINK,
    LOGIN_SECOND,
    CHANNEL_SELECTED,
    PIC_RESPONSE,
    SERVERSTATUS,
    GENDER_SET,
    PIN_OPERATION,
    PIN_ASSIGNED,
    ALL_CHARLIST,
    SERVERLIST,
    CHARLIST,
    SERVER_IP,
    CHAR_NAME_RESPONSE,
    ADD_NEW_CHAR_ENTRY,
    DELETE_CHAR_RESPONSE,
    CHANGE_CHANNEL,
    CS_USE,
    RELOG_RESPONSE,
    REGISTER_PIC_RESPONSE,
    ENABLE_RECOMMENDED,
    SEND_RECOMMENDED,
    PART_TIME,
    SPECIAL_CREATION,
    SECONDPW_ERROR,
    CHANGE_BACKGROUND,
    // Channel
    INVENTORY_OPERATION,
    INVENTORY_GROW,
    UPDATE_STATS,
    GIVE_BUFF,
    CANCEL_BUFF,
    TEMP_STATS,
    TEMP_STATS_RESET,
    UPDATE_SKILLS,
    UPDATE_STOLEN_SKILLS,
    TARGET_SKILL,
    FAME_RESPONSE,
    SHOW_STATUS_INFO,
    FULL_CLIENT_DOWNLOAD,
    SHOW_NOTES,
    TROCK_LOCATIONS,
    LIE_DETECTOR,
    REPORT_RESPONSE,
    REPORT_TIME,
    REPORT_STATUS,
    UPDATE_MOUNT,
    SHOW_QUEST_COMPLETION,
    SEND_TITLE_BOX,
    USE_SKILL_BOOK,
    SP_RESET,
    AP_RESET,
    DISTRIBUTE_ITEM,
    EXPAND_CHARACTER_SLOTS,
    FINISH_GATHER,
    FINISH_SORT,
    EXP_POTION,
    REPORT_RESULT,
    TRADE_LIMIT,
    UPDATE_GENDER,
    BBS_OPERATION,
    CHAR_INFO,
    PARTY_OPERATION,
    MEMBER_SEARCH,
    PARTY_SEARCH,
    BOOK_INFO,
    CODEX_INFO_RESPONSE,
    EXPEDITION_OPERATION,
    BUDDYLIST,
    GUILD_OPERATION,
    ALLIANCE_OPERATION,
    SPAWN_PORTAL,
    MECH_PORTAL,
    ECHO_MESSAGE,
    SERVERMESSAGE,
    ITEM_OBTAIN,
    PIGMI_REWARD,
    OWL_OF_MINERVA,
    OWL_RESULT,
    ENGAGE_REQUEST,
    ENGAGE_RESULT,
    WEDDING_GIFT,
    WEDDING_MAP_TRANSFER,
    USE_CASH_PET_FOOD,
    YELLOW_CHAT,
    SHOP_DISCOUNT,
    CATCH_MOB,
    MAKE_PLAYER_NPC,
    PLAYER_NPC,
    DISABLE_NPC,
    GET_CARD,
    CARD_UNK,
    CARD_SET,
    BOOK_STATS,
    UPDATE_CODEX,
    CARD_DROPS,
    FAMILIAR_INFO,
    CHANGE_HOUR,
    RESET_MINIMAP,
    CONSULT_UPDATE,
    CLASS_UPDATE,
    WEB_BOARD_UPDATE,
    SESSION_VALUE,
    PARTY_VALUE,
    MAP_VALUE,
    EXP_BONUS,
    POTION_BONUS,
    SEND_PEDIGREE,
    OPEN_FAMILY,
    FAMILY_MESSAGE,
    FAMILY_INVITE,
    FAMILY_JUNIOR,
    SENIOR_MESSAGE,
    FAMILY,
    REP_INCREASE,
    EVOLVING_ACTION,
    FAMILY_LOGGEDIN,
    FAMILY_BUFF,
    FAMILY_USE_REQUEST,
    LEVEL_UPDATE,
    MARRIAGE_UPDATE,
    JOB_UPDATE,
    MAPLE_TV_MSG,
    AVATAR_MEGA_RESULT,
    AVATAR_MEGA,
    AVATAR_MEGA_REMOVE,
    POPUP2,
    CANCEL_NAME_CHANGE,
    CANCEL_WORLD_TRANSFER,
    CLOSE_HIRED_MERCHANT,
    GM_POLICE,
    TREASURE_BOX,
    NEW_YEAR_CARD,
    RANDOM_MORPH,
    CANCEL_NAME_CHANGE_2,
    SLOT_UPDATE,
    FOLLOW_REQUEST,
    TOP_MSG,
    MID_MSG,
    CLEAR_MID_MSG,
    SPECIAL_MSG,
    MAPLE_ADMIN_MSG,
    CAKE_VS_PIE_MSG,
    GM_STORY_BOARD,
    INVENTORY_FULL,
    UPDATE_JAGUAR,
    YOUR_INFORMATION,
    FIND_FRIEND,
    VISITOR,
    PINKBEAN_CHOCO,
    PAM_SONG,
    AUTO_CC_MSG,
    DISALLOW_DELIVERY_QUEST,
    ULTIMATE_EXPLORER,
    SPECIAL_STAT,
    UPDATE_IMP_TIME,
    ITEM_POT,
    MULUNG_MESSAGE,
    GIVE_CHARACTER_SKILL,
    MULUNG_DOJO_RANKING,
    UPDATE_INNER_ABILITY,
    EQUIP_STOLEN_SKILL,
    REPLACE_SKILLS,
    INNER_ABILITY_MSG,
    ENABLE_INNER_ABILITY,
    DISABLE_INNER_ABILITY,
    UPDATE_HONOUR,
    AZWAN_UNKNOWN,
    AZWAN_RESULT,
    AZWAN_KILLED,
    CIRCULATOR_ON_LEVEL,
    SILENT_CRUSADE_MSG,
    SILENT_CRUSADE_SHOP,
    CASSANDRAS_COLLECTION,
    SET_OBJECT_STATE,
    POPUP,
    MINIMAP_ARROW,
    UNLOCK_CHARGE_SKILL,
    LOCK_CHARGE_SKILL,
    CANDY_RANKING,
    ATTENDANCE,
    MESSENGER_OPEN,
    EVENT_CROWN,
    MAGIC_WHEEL,
    REWARD,
    SKILL_MACRO,
    WARP_TO_MAP,
    FARM_OPEN,
    CS_OPEN,
    REMOVE_BG_LAYER,
    SET_MAP_OBJECT_VISIBLE,
    RESET_SCREEN,
    MAP_BLOCKED,
    SERVER_BLOCKED,
    PARTY_BLOCKED,
    SHOW_EQUIP_EFFECT,
    MULTICHAT,
    WHISPER,
    SPOUSE_CHAT,
    BOSS_ENV,
    MOVE_ENV,
    UPDATE_ENV,
    MAP_EFFECT,
    CASH_SONG,
    GM_EFFECT,
    OX_QUIZ,
    GMEVENT_INSTRUCTIONS,
    CLOCK,
    BOAT_MOVE,
    BOAT_STATE,
    STOP_CLOCK,
    ARIANT_SCOREBOARD,
    PYRAMID_UPDATE,
    PYRAMID_RESULT,
    QUICK_SLOT,
    MOVE_PLATFORM,
    PYRAMID_KILL_COUNT,
    PVP_INFO,
    DIRECTION_STATUS,
    GAIN_FORCE,
    INTRUSION,
    DIFFERENT_IP,
    ACHIEVEMENT_RATIO,
    QUICK_MOVE,
    SPAWN_OBTACLE_ATOM,
    SPAWN_PLAYER,
    REMOVE_PLAYER_FROM_MAP,
    CHATTEXT,
    CHATTEXT_1,
    CHALKBOARD,
    UPDATE_CHAR_BOX,
    SHOW_CONSUME_EFFECT,
    SHOW_SCROLL_EFFECT,
    SHOW_MAGNIFYING_EFFECT,
    SHOW_POTENTIAL_RESET,
    SHOW_FIREWORKS_EFFECT,
    SHOW_NEBULITE_EFFECT,
    SHOW_FUSION_EFFECT,
    PVP_ATTACK,
    PVP_MIST,
    PVP_COOL,
    TESLA_TRIANGLE,
    FOLLOW_EFFECT,
    SHOW_PQ_REWARD,
    CRAFT_EFFECT,
    CRAFT_COMPLETE,
    HARVESTED,
    PLAYER_DAMAGED,
    NETT_PYRAMID,
    SET_PHASE,
    PAMS_SONG,
    SPAWN_PET,
    SPAWN_PET_2,
    MOVE_PET,
    PET_CHAT,
    PET_NAMECHANGE,
    PET_EXCEPTION_LIST,
    PET_COLOR,
    PET_SIZE,
    PET_COMMAND,
    DRAGON_SPAWN,
    INNER_ABILITY_RESET_MSG,
    DRAGON_MOVE,
    DRAGON_REMOVE,
    ANDROID_SPAWN,
    ANDROID_MOVE,
    ANDROID_EMOTION,
    ANDROID_UPDATE,
    ANDROID_DEACTIVATED,
    SPAWN_FAMILIAR,
    MOVE_FAMILIAR,
    TOUCH_FAMILIAR,
    ATTACK_FAMILIAR,
    RENAME_FAMILIAR,
    SPAWN_FAMILIAR_2,
    UPDATE_FAMILIAR,
    HAKU_CHANGE_1,
    HAKU_CHANGE_0,
    HAKU_MOVE,
    HAKU_UNK,
    HAKU_CHANGE,
    SPAWN_HAKU,
    MOVE_PLAYER,
    CLOSE_RANGE_ATTACK,
    RANGED_ATTACK,
    MAGIC_ATTACK,
    ENERGY_ATTACK,
    SKILL_EFFECT,
    MOVE_ATTACK,
    CANCEL_SKILL_EFFECT,
    DAMAGE_PLAYER,
    FACIAL_EXPRESSION,
    SHOW_EFFECT,
    SHOW_TITLE,
    ANGELIC_CHANGE,
    SHOW_CHAIR,
    UPDATE_CHAR_LOOK,
    SHOW_FOREIGN_EFFECT,
    GIVE_FOREIGN_BUFF,
    CANCEL_FOREIGN_BUFF,
    UPDATE_PARTYMEMBER_HP,
    LOAD_GUILD_NAME,
    LOAD_GUILD_ICON,
    LOAD_TEAM,
    SHOW_HARVEST,
    PVP_HP,
    CANCEL_CHAIR,
    DIRECTION_FACIAL_EXPRESSION,
    MOVE_SCREEN,
    SHOW_SPECIAL_EFFECT,
    CURRENT_MAP_WARP,
    MESOBAG_SUCCESS,
    MESOBAG_FAILURE,
    R_MESOBAG_SUCCESS,
    R_MESOBAG_FAILURE,
    MAP_FADE,
    MAP_FADE_FORCE,
    UPDATE_QUEST_INFO,
    HP_DECREASE,
    PLAYER_HINT,
    PLAY_EVENT_SOUND,
    PLAY_MINIGAME_SOUND,
    MAKER_SKILL,
    OPEN_UI,
    OPEN_UI_OPTION,
    INTRO_LOCK,
    INTRO_ENABLE_UI,
    INTRO_DISABLE_UI,
    SUMMON_HINT,
    SUMMON_HINT_MSG,
    ARAN_COMBO,
    ARAN_COMBO_RECHARGE,
    RANDOM_EMOTION,
    RADIO_SCHEDULE,
    OPEN_SKILL_GUIDE,
    GAME_MSG,
    GAME_MESSAGE,
    BUFF_ZONE_EFFECT,
    GO_CASHSHOP_SN,
    DAMAGE_METER,
    TIME_BOMB_ATTACK,
    FOLLOW_MOVE,
    FOLLOW_MSG,
    AP_SP_EVENT,
    QUEST_GUIDE_NPC,
    REGISTER_FAMILIAR,
    FAMILIAR_MESSAGE,
    CREATE_ULTIMATE,
    HARVEST_MESSAGE,
    SHOW_MAP_NAME,
    OPEN_BAG,
    DRAGON_BLINK,
    PVP_ICEGAGE,
    DIRECTION_INFO,
    REISSUE_MEDAL,
    PLAY_MOVIE,
    CAKE_VS_PIE,
    PHANTOM_CARD,
    LUMINOUS_COMBO,
    MOVE_SCREEN_X,
    MOVE_SCREEN_DOWN,
    CAKE_PIE_INSTRUMENTS,
    COOLDOWN,
    SPAWN_SUMMON,
    REMOVE_SUMMON,
    MOVE_SUMMON,
    SUMMON_ATTACK,
    PVP_SUMMON,
    SUMMON_SKILL,
    SUMMON_SKILL_2,
    SUMMON_DELAY,
    DAMAGE_SUMMON,
    SPAWN_MONSTER,
    KILL_MONSTER,
    SPAWN_MONSTER_CONTROL,
    MOVE_MONSTER,
    MOVE_MONSTER_RESPONSE,
    APPLY_MONSTER_STATUS,
    CANCEL_MONSTER_STATUS,
    DAMAGE_MONSTER,
    SKILL_EFFECT_MOB,
    TELE_MONSTER,
    MONSTER_SKILL,
    MONSTER_CRC_CHANGE,
    SHOW_MONSTER_HP,
    SHOW_MAGNET,
    ITEM_EFFECT_MOB,
    CATCH_MONSTER,
    MONSTER_PROPERTIES,
    REMOVE_TALK_MONSTER,
    TALK_MONSTER,
    CYGNUS_ATTACK,
    MONSTER_RESIST,
    MOB_TO_MOB_DAMAGE,
    AZWAN_MOB_TO_MOB_DAMAGE,
    AZWAN_SPAWN_MONSTER,
    AZWAN_KILL_MONSTER,
    AZWAN_SPAWN_MONSTER_CONTROL,
    SPAWN_NPC,
    REMOVE_NPC,
    SPAWN_NPC_REQUEST_CONTROLLER,
    NPC_ACTION,
    NPC_TOGGLE_VISIBLE,
    INITIAL_QUIZ,
    NPC_UPDATE_LIMITED_INFO,
    NPC_SET_SPECIAL_ACTION,
    NPC_SCRIPTABLE,
    RED_LEAF_HIGH,
    SPAWN_HIRED_MERCHANT,
    DESTROY_HIRED_MERCHANT,
    UPDATE_HIRED_MERCHANT,
    DROP_ITEM_FROM_MAPOBJECT,
    REMOVE_ITEM_FROM_MAP,
    SPAWN_KITE_ERROR,
    SPAWN_KITE,
    DESTROY_KITE,
    SPAWN_MIST,
    REMOVE_MIST,
    SPAWN_DOOR,
    REMOVE_DOOR,
    BUTT_FUCKIN,
    MECH_DOOR_SPAWN,
    MECH_DOOR_REMOVE,
    REACTOR_HIT,
    REACTOR_MOVE,
    REACTOR_SPAWN,
    REACTOR_DESTROY,
    SPAWN_EXTRACTOR,
    REMOVE_EXTRACTOR,
    ROLL_SNOWBALL,
    HIT_SNOWBALL,
    SNOWBALL_MESSAGE,
    LEFT_KNOCK_BACK,
    HIT_COCONUT,
    COCONUT_SCORE,
    MOVE_HEALER,
    PULLEY_STATE,
    MONSTER_CARNIVAL_START,
    MONSTER_CARNIVAL_OBTAINED_CP,
    MONSTER_CARNIVAL_STATS,
    MONSTER_CARNIVAL_SUMMON,
    MONSTER_CARNIVAL_MESSAGE,
    MONSTER_CARNIVAL_DIED,
    MONSTER_CARNIVAL_LEAVE,
    MONSTER_CARNIVAL_RESULT,
    MONSTER_CARNIVAL_RANKING,
    ARIANT_SCORE_UPDATE,
    SHEEP_RANCH_INFO,
    SHEEP_RANCH_CLOTHES,
    WITCH_TOWER,
    EXPEDITION_CHALLENGE,
    ZAKUM_SHRINE,
    CHAOS_ZAKUM_SHRINE,
    PVP_TYPE,
    PVP_TRANSFORM,
    PVP_DETAILS,
    PVP_ENABLED,
    PVP_SCORE,
    PVP_RESULT,
    PVP_TEAM,
    PVP_SCOREBOARD,
    PVP_POINTS,
    PVP_KILLED,
    PVP_MODE,
    PVP_ICEKNIGHT,
    HORNTAIL_SHRINE,
    CAPTURE_FLAGS,
    CAPTURE_POSITION,
    CAPTURE_RESET,
    PINK_ZAKUM_SHRINE,
    NPC_TALK,
    OPEN_NPC_SHOP,
    CONFIRM_SHOP_TRANSACTION,
    OPEN_STORAGE,
    MERCH_ITEM_MSG,
    MERCH_ITEM_STORE,
    RPS_GAME,
    MESSENGER,
    PLAYER_INTERACTION,
    VICIOUS_HAMMER,
    LOGOUT_GIFT,
    TOURNAMENT,
    TOURNAMENT_MATCH_TABLE,
    TOURNAMENT_SET_PRIZE,
    TOURNAMENT_UEW,
    TOURNAMENT_CHARACTERS,
    SEALED_BOX,
    WEDDING_PROGRESS,
    WEDDING_CEREMONY_END,
    PACKAGE_OPERATION,
    CS_CHARGE_CASH,
    CS_EXP_PURCHASE,
    GIFT_RESULT,
    CHANGE_NAME_CHECK,
    CHANGE_NAME_RESPONSE,
    CS_UPDATE,
    CS_OPERATION,
    CS_MESO_UPDATE,
    //0x314 int itemid int sn
    CASH_SHOP,
    CASH_SHOP_UPDATE,
    GACHAPON_STAMPS,
    FREE_CASH_ITEM,
    CS_SURPRISE,
    XMAS_SURPRISE,
    ONE_A_DAY,
    NX_SPEND_GIFT,
    RECEIVE_GIFT,
    KEYMAP,
    PET_AUTO_HP,
    PET_AUTO_MP,
    PET_AUTO_CURE,
    START_TV,
    REMOVE_TV,
    ENABLE_TV,
    GM_ERROR,
    ALIEN_SOCKET_CREATOR,
    GOLDEN_HAMMER,
    BATTLE_RECORD_DAMAGE_INFO,
    CALCULATE_REQUEST_RESULT,
    BOOSTER_PACK,
    BOOSTER_FAMILIAR,
    BLOCK_PORTAL,
    NPC_CONFIRM,
    RSA_KEY,
    LOGIN_AUTH,
    PET_FLAG_CHANGE,
    BUFF_BAR,
    GAME_POLL_REPLY,
    GAME_POLL_QUESTION,
    ENGLISH_QUIZ,
    FISHING_BOARD_UPDATE,
    BOAT_EFFECT,
    FISHING_CAUGHT,
    SIDEKICK_OPERATION,
    FARM_PACKET1,
    FARM_ITEM_PURCHASED,
    FARM_ITEM_GAIN,
    HARVEST_WARU,
    FARM_MONSTER_GAIN,
    FARM_INFO,
    FARM_MONSTER_INFO,
    FARM_QUEST_DATA,
    FARM_QUEST_INFO,
    FARM_MESSAGE,
    UPDATE_MONSTER,
    AESTHETIC_POINT,
    UPDATE_WARU,
    FARM_EXP,
    FARM_PACKET4,
    QUEST_ALERT,
    FARM_PACKET8,
    FARM_FRIENDS_BUDDY_REQUEST,
    FARM_FRIENDS,
    FARM_USER_INFO,
    FARM_AVATAR,
    FRIEND_INFO,
    FARM_RANKING,
    SPAWN_FARM_MONSTER1,
    SPAWN_FARM_MONSTER2,
    RENAME_MONSTER,
    STRENGTHEN_UI,
    //Unplaced:
    MAPLE_POINT,
    DEATH_COUNT,

    REDIRECTOR_COMMAND,
    
    SHOW_DAMAGE_SKIN;
    private int code = -2;

    @Override
    public void setValue(short code) {
        this.code = code;
    }

    public static String getOpcodeName(int value) {

        for (SendPacketOpcode opcode : values()) {
            if (opcode.getValue() == value) {
                System.out.println("DEBUG[SEND]: " + opcode.name());
                return opcode.name();
            }
        }
        return "UNKNOWN";
    }

    @Override
    public short getValue() {
        System.out.println("Packet to send: " + this.name() + " Value: " + this.code + "\r\nCaller: " + Thread.currentThread().getStackTrace()[2]);
        System.out.println("[S]Header: " + this.name());
        return (short) code;
    }

    public static Properties getDefaultProperties() throws FileNotFoundException, IOException {
        Properties props = new Properties();
        FileInputStream fileInputStream = new FileInputStream(GameConstants.GMS ? "sendopsGMS.properties" : "sendops.properties");
        props.load(fileInputStream);
        fileInputStream.close();
        return props;
    }

    public static final void reloadValues() {
        try {
            ExternalCodeTableGetter.populateValues(getDefaultProperties(), values());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load sendops", e);
        }
    }

    static {
        reloadValues();
    }
}