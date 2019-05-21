/*
	This file is part of the OdinMS Maple Story Server
	Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc> 
					   Matthias Butz <matze@odinms.de>
					   Jan Christian Meyer <vimes@odinms.de>

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as
	published by the Free Software Foundation version 3 as published by
	the Free Software Foundation. You may not use, modify or distribute
	this program under any other version of the GNU Affero General Public
	License.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Guild Alliance NPC
 */

var status;
var choice;
var guildName;
var partymembers;

function start() {
	//cm.sendOk("The Guild Alliance is currently under development.");
	//cm.dispose();
	partymembers = cm.getPartyMembers();
	status = -1;
	action(1,0,0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		cm.dispose();
		return;
	}
	if (status == 0) {
		cm.sendSimple("Xin ch�o! T�i l� #bLenario#k\r\n#b#L0#B�n c� th� cho bi�t Li�n minh h�i l� g� kh�ng#l\r\n#L1#L�m sao �� t�o Li�n Minh H�i?#l\r\n#L2#T�i mu�n t�o Li�n Minh H�i.#l\r\n#L3#T�i mu�n t�ng kh� n�ng c�a Li�n Minh H�i.#l\r\n#L4#T�i mu�n r�i kh�i Li�n Minh H�i.#l");
	} else if (status == 1) {
		choice = selection;
	    if (selection == 0) {
		    cm.sendOk("Li�n minh h�i c�ng gi�ng nh� n� n�i, m�t li�n minh c�a m�t s� h�i �� t�o th�nh. T�i ch�u tr�ch nhi�m qu�n l� c�c Li�n minh h�i.");
			cm.dispose();
		} else if (selection == 1) {
			cm.sendOk("к t�o Li�n minh h�i t�i c�n 2 ch� h�i � trong m�t nh�m.");
			cm.dispose();
		} else if(selection == 2) {
			if (cm.getPlayer().getParty() == null || partymembers == null || partymembers.size() != 2 || !cm.isLeader()) {
				cm.sendOk("B�n kh�ng th� t�o li�n minh n�u trong nh�m kh�ng c� �� 2 th�nh vi�n ch� h�i"); //Not real text
				cm.dispose();
			} else if (partymembers.get(0).getGuildId() <= 0 || partymembers.get(0).getGuildRank() > 1) {
				cm.sendOk("B�n kh�ng ph�i l� ch� m�t h�i");
				cm.dispose();
			} else if (partymembers.get(1).getGuildId() <= 0 || partymembers.get(1).getGuildRank() > 1) {
				cm.sendOk("B�n kh�ng ph�i l� th�nh vi�n c�a nh�m.");
				cm.dispose();
			} else {
				var gs = cm.getGuild(cm.getPlayer().getGuildId());
				var gs2 = cm.getGuild(partymembers.get(1).getGuildId());
				if (gs.getAllianceId() > 0) {
					cm.sendOk("B�n kh�ng th� t�o li�n minh n�u b�n �ang li�n k�t v�i m�t Li�n minh kh�c.");
					cm.dispose();
				} else if (gs2.getAllianceId() > 0) {
					cm.sendOk("Th�nh vi�n trong nh�m c�a b�n �ang c� m�t li�n minh kh�c.");
					cm.dispose();
				} else if (cm.partyMembersInMap() < 2) {
					cm.sendOk("T�i c�n ng��i trog nh�m c� m�t trong b�n �� n�y.");
					cm.dispose();
				} else
                			cm.sendYesNo("B�n c� quan t�m t�i th�nh l�p Li�n Minh h�i?");
			}
		} else if (selection == 3) {
			if (cm.getPlayer().getGuildRank() == 1 && cm.getPlayer().getAllianceRank() == 1) {
				cm.sendYesNo("N�ng t�nh n�ng c�a Li�n minh h�i v�i chi ph� 10,000,000 mesos. B�n c� mu�n ti�p t�c ?"); //ExpandGuild Text
			} else {
			    cm.sendOk("Ch� li�n minh m�i ���c s� d�ng t�nh n�ng n�y.");
				cm.dispose();
			}
		} else if(selection == 4) {
			if (cm.getPlayer().getGuildRank() == 1 && cm.getPlayer().getAllianceRank() == 1) {
				cm.sendYesNo("b�n c� mu�n r�i kh�i li�n minh?");
			} else {
				cm.sendOk("Ch� c� ch� li�n minh m�i ���c h�y Li�n minh.");
				cm.dispose();
			}
		}
	} else if(status == 2) {
	    if (choice == 2) {
		    cm.sendGetText("H�y nh�p t�n h�i m�i c�a b�n. (max. 12 letters)");
		} else if (choice == 3) {
			if (cm.getPlayer().getGuildId() <= 0) {
				cm.sendOk("B�n kh�ng th� t�ng li�n minh kh�ng t�n t�i.");
				cm.dispose();
			} else {
				if (cm.addCapacityToAlliance()) {
					cm.sendOk("B�n �� th�m s� l��ng th�nh vi�n v�o li�n minh.");
				} else {
					cm.sendOk("S� l��ng t�i �a trong li�n minh l� 5 h�i.");
				}
				cm.dispose();
			}
		} else if (choice == 4) {
			if (cm.getPlayer().getGuildId() <= 0) {
				cm.sendOk("B�n kh�ng th� gi�i t�n Li�n minh kh�ng t�n t�i.");
				cm.dispose();
			} else {
				if (cm.disbandAlliance()) {
					cm.sendOk("Your Guild Union has been disbanded");
				} else {
					cm.sendOk("An error occured when disbanding the Guild Union");
				}
				cm.dispose();
			}
		}
	} else if (status == 3) {
		guildName = cm.getText();
	    cm.sendYesNo("T�n #b"+ guildName + "#k l� t�n c�a Li�n minh h�i?");
	} else if (status == 4) {
			if (!cm.createAlliance(guildName)) {
				cm.sendNext("H�y ch�n m�t t�n kh�c"); //Not real text
				status = 1;
				choice = 2;
			} else
				cm.sendOk("B�n �� t�o th�nh c�ng.");
			cm.dispose();
	}
}