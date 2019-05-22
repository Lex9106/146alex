function enter(pi) {
    if (pi.getPlayer().getMapId() != 970000000) {
        pi.getPlayer().saveLocation("FREE_MARKET");
        pi.warp(970000000, "out00");
        return true;
    }
    return false;
}