/* Author: Xterminator
	NPC Name: 		Kyrin
	Map(s): 		The Nautilus : Navigation Room (120000101)
	Description: 		H�i T�c Instructor
*/

var status = 0;
var requirements = false;
var text;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (((status == 0 || status == 1 || status == 9) && mode == 0) || ((status == 8 || status == 12 || status == 16 || status == 18 || status == 21) && mode == 1)) {
	cm.dispose();
	return;
    } else if (status == 2 && mode == 0 && requirements) {
	cm.sendNext("T�i hi�u ... V�ng, vi�c ch�n m�t c�ng vi�c m�i l� m�t quy�t ��nh r�t quan tr�ng. N�u b�n �� s�n s�ng, h�y cho t�i bi�t!");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	if (cm.getMapId() == 912010200) {
	    status = 100;
	    cm.sendNext("Kh�ng t� ch�t n�o! H�y th�o lu�n �i�u n�y b�n ngo�i!");
	} else {
	    var tosent = "B�n c� g� �� n�i kh�ng? #b\r\n#L0#T�i mu�n t�m hi�u th�m v� V� s�...#l";
	    if (cm.getQuestStatus(6370) == 1 || cm.getQuestStatus(6330) == 1) {
		tosent += "\r\n#L1#T�i �� s�n s�ng chi�n ��u ch�ng l�i b�n.#l"
	    }
	    cm.sendSimple(tosent);
	}
    } else if (status == 1) {
	if (cm.getJob() == 510 || cm.getJob() == 520 || cm.getJob() == 530) {
	    if (cm.getPlayerStat("LVL") < 70) {
		if (cm.getJob() == 510) {
		    cm.sendNext("Ohhh, �� l� b�n. L�m th� n�o gi�ng nh� m�t V� s�? B�n tr�ng cao c�p h�n nhi�u v� ��nh b�ng h�n l�n cu�i c�ng t�i nh�n th�y b�n. Hy v�ng nh�ng �i�u tuy�t v�i d�nh cho b�n trong t��ng lai.");
		} else if (cm.getJob() == 530) {
		    cm.sendNext("Ohhh, �� l� b�n. L�m th� n�o gi�ng nh� m�t V� s�? B�n tr�ng cao c�p h�n nhi�u v� ��nh b�ng h�n l�n cu�i c�ng t�i nh�n th�y b�n. Hy v�ng nh�ng �i�u tuy�t v�i d�nh cho b�n trong t��ng lai.");
		} else {
		    cm.sendNext("Ohhh, �� l� b�n. L�m th� n�o gi�ng nh� m�t V� s�? B�n tr�ng cao c�p h�n nhi�u v� ��nh b�ng h�n l�n cu�i c�ng t�i nh�n th�y b�n. Hy v�ng nh�ng �i�u tuy�t v�i d�nh cho b�n trong t��ng lai.");
		}
	    } else {
			cm.sendNext("�i g�p Th�y H�i T�c � L�ng Tuy�t. Anh �y s� gi�p b�n tr� n�n m�nh m� h�n.");
		}
	    cm.dispose();
	} else if (cm.getJob() == 500) {
	    if (cm.getPlayerStat("LVL") < 30) {
		status = 9;
		cm.sendSimple("B�n c� �i�u g� khi�n b�n khao kh�t tr� th�nh H�i t�c...?#b\r\n#L0#C�c t�nh n�ng c� b�n c�a H�i t�c l� g�??#l\r\n#L1#V� kh� m� h�i t�c s� d�ng l� g�?#l\r\n#L2#Trang b� m� h�i t�c mang tr�n ng��i?#l\r\n#L3#K� n�ng c�a H�i t�c nh� th� n�o?");
	    } else if (cm.getPlayerStat("LVL") >= 30) {
		status = 22;
		cm.sendSimple("B�n c� mu�n bi�t th�m v� V� s� v� Th�n s�ng kh�ng? N� s� l� t�t �� bi�t tr��c, v� v�y b�n s� c� m�t h�nh �nh r� r�ng v� nh�ng g� b�n mu�n tr� th�nh cho ti�n b� c�ng vi�c c�a b�n...\r\n#b#L0# H�y gi�i th�ch cho t�i v� V� s�.#k#l\r\n#b#L1# H�y gi�i th�ch cho t�i v� th�n s�ng.#k#l");
	    }
	} else if (cm.getJob() == 501) {
		if (cm.getPlayerStat("LVL") < 30) {
			status = 9;
			cm.sendSimple("B�n c� �i�u g� khi�n b�n khao kh�t tr� th�nh H�i t�c...?#b\r\n#L0#C�c t�nh n�ng c� b�n c�a H�i t�c l� g�??#l\r\n#L1#V� kh� m� h�i t�c s� d�ng l� g�?#l\r\n#L2#Trang b� m� h�i t�c mang tr�n ng��i?#l\r\n#L3#K� n�ng c�a H�i t�c nh� th� n�o?");
		} else if (cm.getPlayerStat("LVL") >= 30) {
			status = 44;
			cm.sendSimple("B�n c� mu�n bi�t th�m v� Cannoneers? N� s� l� t�t �� bi�t tr��c, v� v�y b�n s� c� m�t h�nh �nh r� r�ng v� nh�ng g� b�n mu�n tr� th�nh cho ti�n b� c�ng vi�c c�a b�n...\r\n#b#L0# Please explain to me what being a Cannoneer is all about.#k#l");
	    }
	} else if (cm.getJob() == 0 || cm.getJob() == 1) {
	    cm.sendNext("B�n c� mu�n tr� th�nh m�t H�i T�c? B�n s� c�n ph�i ��p �ng b� ti�u chu�n c�a ch�ng t�i n�u b�n mu�n tr� th�nh m�t trong s� ch�ng t�i. T�i c�n b�n �t nh�t l� #b Level 10, v�i kh�o l�o � m�c 20 ho�c cao h�n. #k H�y xem...");
	} else {
	    if (selection == 0) {
		cm.sendNext("B�n kh�ng mu�n c�m th�y t� do ph�t ra t� bi�n? B�n kh�ng mu�n s�c m�nh, s� n�i ti�ng, v� m�i th� kh�c �i k�m v�i n�? Sau ��, b�n n�n tham gia v�i ch�ng t�i v� t�n h��ng n� cho m�nh.");
		cm.dispose();
	    } else if (selection == 1 && (cm.getQuestStatus(6370) == 1 || cm.getQuestStatus(6330) == 1)) {
		status = 99;
		cm.sendNext("B�n �� s�n s�ng, ph�i kh�ng? B�y gi� c� g�ng ch�ng l�i c�c cu�c t�n c�ng c�a t�i trong 2 ph�t. T�i s� kh�ng d� d�ng v�i b�n. Ch�c may m�n, b�i v� b�n s� c�n n�.");
	    }
	}
    } else if (status == 2) {
	if (cm.getPlayerStat("LVL") >= 10) {
	    requirements = true;
	    cm.sendYesNo("B�n nh�n nhi�u h�n �� �i�u ki�n! Tuy�t v�i, b�n �� s�n s�ng �� tr� th�nh m�t trong s� ch�ng t�i?");
	} else {
	    cm.sendNext("Hmm ... T�i kh�ng ngh� b�n �� ��o t�o �� r�i. H�n g�p t�i khi b�n m�nh h�n.");
	}
    } else if (status == 3) {
	if (requirements)
	    cm.sendNext("Ch�o m�ng ��n v�i ban nh�c H�i T�cs! B�n c� th� ph�i d�nh th�i gian l�m ng��i lang thang l�c ��u, nh�ng ng�y t�t h�n ch�c ch�n s� b�nh minh cho b�n, s�m h�n b�n ngh�! Trong th�i gian ch� ��i, h�y �� t�i chia s� m�t s� kh� n�ng c�a t�i v�i b�n.");
	else
	    cm.dispose();
    } else if (status == 4) {
	if (cm.getJob() == 1 || (cm.getJob() == 0 && cm.getPlayer().getSubCategoryField() > 1)) {
	    cm.changeJob(501);
	    cm.resetStats(4, 4, 4, 4);
		cm.gainItem(1532000, 1);
		cm.gainSp(1);
	} else if (cm.getJob() == 0) {
	    cm.changeJob(500);
	    cm.resetStats(4, 20, 4, 4);
	    cm.gainItem(1482014, 1);
	    cm.gainItem(1492014, 1);
	    cm.gainItem(2330006, 600);
	    cm.gainItem(2330006, 600);
	    cm.gainItem(2330006, 600);
	    cm.gainSp(1);
	}
	cm.sendNext("T�i v�a t�ng s� l��ng � tr�ng trong t�i �� c�a b�n v� v.v. h�nh l�. B�n c�ng �� nh�n ���c m�t ch�t m�nh m� h�n. B�n c� c�m nh�n ���c kh�ng? B�y gi� b�n c� th� ch�nh th�c g�i m�nh l� H�i T�c, h�y tham gia c�ng ch�ng t�i trong nhi�m v� phi�u l�u v� t� do c�a ch�ng t�i!");
    } else if (status == 5) {
	cm.sendNext("T�i ch� cho b�n m�t ch�t # bSP #k. H�y xem #bSkill menu #k �� t�m m�t s� k� n�ng v� s� d�ng SP c�a b�n �� h�c c�c k� n�ng. H�y coi ch�ng r�ng kh�ng ph�i t�t c� c�c k� n�ng ��u c� th� ���c t�ng c��ng t� khi b�t ��u. C� m�t s� k� n�ng m� b�n ch� c� th� c� ���c sau khi l�m ch� c�c k� n�ng c� b�n.");
    } else if (status == 6) {
	cm.sendNext("M�t �i�u n�a. B�y gi� b�n �� t�t nghi�p t� Ranks c�a m�t ng��i m�i b�t ��u th�nh m�t H�i T�c, b�n s� ph�i ch�c ch�n r�ng b�n kh�ng ch�t s�m. N�u b�n m�t t�t c� s�c kh�e c�a m�nh, b�n s� m�t EXP ��ng gi� m� b�n �� ki�m ���c. N� s� kh�ng b�c m�i �� l�m m�t EXP kh� ki�m ���c b�ng c�ch ch�t?");
    } else if (status == 7) {
	cm.sendNext("��y l� t�t c� nh�ng g� t�i c� th� d�y cho b�n. T�i c�ng �� cung c�p cho b�n m�t s� v� kh� h�u �ch �� l�m vi�c v�i, do ��, n� thu�c v�o b�n b�y gi� �� ��o t�o v�i h�. Th� gi�i l� c�a b�n �� tham gia, v� v�y h�y s� d�ng t�i nguy�n c�a b�n m�t c�ch kh�n ngoan, v� khi b�n c�m th�y nh� b�n �� ��t ��n ��nh cao, h�y cho t�i bi�t. T�i s� c� m�t c�i g� �� t�t h�n cho b�n trong c�a h�ng...");
    } else if (status == 8) {
	cm.sendNext("Oh, v� ... n�u b�n c� th�m c�u h�i v� vi�c l� m�t H�i T�c, ho�c n�u b�n c�n m�t s� con tr� ... b�n lu�n c� th� h�i t�i. T�i s� g�p b�n...");
    } else if (status == 10) {
	if (selection == 0) {
	    status = 11;
	    text = "��y l� nh�ng g� b�n c�n bi�t v� vi�c tr� th�nh m�t t�n V� s�. B�n c� th� ngh� r�ng H�i T�c l� m�t con ���ng l�n cung c�p nhi�u con ���ng. N�u b�n mu�n th�ng tr� qu�i v�t v�i s�c m�nh v� phu, h�y t�p trung v�o vi�c c�i thi�n S�c m�nh. N�u b�n mu�n outsmart nh�ng con qu�i v�t v�i c�c cu�c t�n c�ng t�m xa, t�i �� ngh� b�n t�p trung v�o vi�c c�i thi�n Kh�o l�o.";
	} else if (selection == 1) {
	    status = 13;
	    text = "Kh�ng gi�ng nh� c�c c�ng vi�c kh�c, H�i T�c s� cho ph�p b�n chi�n ��u v�i qu�i v�t b�ng n�m tay tr�n. Tuy nhi�n, n�u b�n mu�n t�i �a h�a kh� n�ng t�n c�ng c�a m�nh, t�i khuy�n b�n n�n s� d�ng Tay ��m ho�c S�ng.";
	} else if (selection == 2) {
	    status = 17;
	    text = "H�i T�c th��ng l� H�m ��i n�i, s� d�ng nhanh ch�ng �� t�n c�ng c�c ��i th� kinh ng�c. V�ng, �i�u n�y c�ng c� ngh�a l� �o gi�p c�ng ph�i nh�. ��y l� l� do t�i sao h�u h�t qu�n �o cho H�i T�c ���c l�m t� v�i.";
	} else {
	    status = 19;
	    text = "��i v�i H�i T�c, c� nh�ng k� n�ng h� tr� t�nh ch�nh x�c v� tr�nh ���c c�n thi�t �� c� hi�u qu�. M�t s� k� n�ng t�n c�ng ch� li�n quan ��n n�m ��m tr�n ho�c s�ng, v� v�y b�n c� th� mu�n ch�n m�t trong hai ph��ng ph�p t�n c�ng v� d�nh v�o n� khi t�ng c�p k� n�ng c�a b�n.";
	}
	cm.sendNext(text);
    } else if (status == 11) {
	cm.sendNext(text);
    } else if (status == 12) {
	cm.sendNext("�� l� m�t c�ng vi�c thay ��i d�a tr�n nh�ng g� b�n l�m v�i n�. B�n n�n suy ngh� v� ph�a tr��c v� x�c ��nh nh�ng g� b�n mu�n tr� th�nh sau n�y, v� v�y b�n c� th� b�t ��u t�p trung v�o hai s� li�u th�ng k� b�n mu�n c�i thi�n, STR ho�c DEX. N�u b�n mu�n tr� th�nh m�t  H�i t�c ��m h�y t�ng S�c m�nh.Th�n s�ng, t�ng Kh�o l�o.");
    } else if (status == 13) {
	cm.sendNext(text);
    } else if (status == 14) {
	cm.sendNext("N�u b�n mu�n tham gia v�o c�c cu�c t�n c�ng c�n chi�n v� g�y cho�ng qu�i v�t, h�y s� d�ng Knuckler. N� tr�ng gi�ng nh� m�ng vu�t m� nh�ng t�n tr�m s� d�ng, nh�ng n� ���c l�m b�ng v�t li�u ch�c ch�n h�n nhi�u �� m� ph�ng v� b�o v� n�m ��m.");
    } else if (status == 15) {
	cm.sendNext("N�u b�n mu�n ��i th� t�m xa, h�y s� d�ng Gun. T�t nhi�n, b�n th�n Gun s� kh�ng l�m �i�u �� cho b�n. B�n s� c�n ��n. B�n c� th� mua ch�ng � b�t k� c�a h�ng ti�n l�i n�o g�n ��.");
    } else if (status == 16) {
	cm.sendNext("N�u b�n mu�n ��i th� t�m xa, h�y s� d�ng Gun. T�t nhi�n, b�n th�n Gun s� kh�ng l�m �i�u �� cho b�n. B�n s� c�n ��n. B�n c� th� mua ch�ng � b�t k� c�a h�ng ti�n l�i n�o g�n ��....");
    } else if (status == 17) {
	cm.sendNext(text);
    } else if (status == 18) {
	cm.sendNext("Phong c�ch t�n c�ng c�a b�n s� kh�c nhau t�y thu�c v�o V� kh� b�n ch�n, v� v�y h�y suy ngh� c�n th�n tr��c khi ch�n. T�t nhi�n, v� kh� b�n s� d�ng c�ng c� th� x�c ��nh nh�ng g� b�n s� tr� th�nh xu�ng ���ng...!");
    } else if (status == 19) {
	cm.sendNext(text);
    } else if (status == 20) {
	cm.sendNext("N�u b�n mu�n s� d�ng s�ng, sau �� t�i �� ngh� b�n s� d�ng k� n�ng #bB�n 2 vi�n #k. B�n 2 vi�n cho ph�p b�n b�n 2 vi�n ��n c�ng m�t l�c, �i�u n�y s� cho ph�p b�n t�n c�ng qu�i v�t t� t�m xa.");
    } else if (status == 21) {
	cm.sendNext("N�u b�n �ang s� d�ng n�m tay tr�n ho�c G�ng ��m, h�y t�p trung v�o #bSommersault Kick #k v� / ho�c #bFlash Fist #k. Thay th� hai k� n�ng n�y �� t�i �a h�a kh� n�ng t�n c�ng c�a b�n. B�n c�ng c� th� s� d�ng nh�ng k� n�ng n�y trong khi mang theo m�t kh�u s�ng, nh�ng n� kh�ng hi�u qu� b�ng c�ch s� d�ng G�ng ��m.");
    } else if (status == 23) {
	if (selection == 0) {
	    status = 24;
	    text = "T�i s� gi�i th�ch cho b�n nh�ng g� m�t V� S�. V� S� l� d�ng c�m v�i n�m ��m tr�n v� knucklers. K� t� khi V� S� c� n�ng l��ng Ch� y�u trong Melee Battles, t�t nh�t b�n n�n s� d�ng c�c k� n�ng t�n c�ng kh�c nhau �� stun nh�ng con qu�i v�t ��u ti�n tr��c khi ti�p t�c t�n c�ng m�nh h�n. S� d�ng #q5101002 ##k �� l�m cho�ng k� th� ��ng sau b�n v� #q5101003 ##k �� l�m cho�ng k� th� tr��c m�t b�n.";
	} else {
	    status = 26;
	    text = "I'll explain to you what being a Gunslinger is all about. Gunslingers are H�i T�cs that can attack enemies from long range with high accuracy. Use #b#q5201001##k or #b#q5201002##k to attack multiple monsters at once.";
	}
	cm.sendNext(text);
    } else if (status == 24) {
	cm.sendNext(text);
    } else if (status == 25) {
	cm.sendNext("One Brawler skill is called #b#q5101007##k. This skill is useful when you use it to leave the area without being detected by the monsters. Basically, it's you disguised as an Oak Barrel, and walking away from danger. Sometimes, a quick-thinking monster may catch you, but the higher your skill level gets, the less possibility of you getting caught red-handed and having to fight your way out.");
    } else if (status == 26) {
	cm.sendNext("Next, we'll talk about #b#q5101005##k. It's a skill that allows you to regain MP at the expense of a bit of HP. Other than the Warriors, Brawlers have the highest HP of all, so losing a bit of HP doesn't affect them as much. It's especially useful when you're in the middle of combat, and you've run out of MP potions. Of course, you'll need to be aware of your HP level and the risks you'll be taking by using the skill.");
	status = 34;
    } else if (status == 27) {
	cm.sendNext("One Gunslinger skill is called #b#q5201006##k. This skill uses the recoil of the gun to let you jump backwards and attack monsters from behind. This skill is especially effective when you are trapped in the middle of monsters and need to escape. Just make sure you have a monster behind you before using this, okay?");
    } else if (status == 28) {
	cm.sendNext("Next, we'll talk about #b#q5201005##k. This skill allows you to jump without being affected by Maple's law of gravity. This will allow you to stay afloat longer, and land on the ground later than regular jumps. If you use #b#q5201005##k from a high place, don't you think you'll be able to attack monsters in midair?");
	status = 39;
    } else if (status == 35) {
	cm.sendNext("B�n mu�n tr� th�nh #bV� S�#k.");
    } else if (status == 36) {
	if (cm.getJob() == 500) {
	    cm.changeJob(510);
	    cm.gainSp(1);
	}
	cm.sendNext("���c r�i, t� ��y tr� �i, b�n l� #bV� S� #k. Brawlers cai tr� th� gi�i b�ng s�c m�nh c�a n�m tay tr�n c�a h� ... c� ngh�a l� h� c�n ph�i luy�n t�p c� th� c�a h� nhi�u h�n nh�ng ng��i kh�c. N�u b�n g�p kh� kh�n trong vi�c ��o t�o, t�i s� r�t s�n l�ng tr� gi�p.");
    } else if (status == 37) {
	cm.sendNext("T�i v�a ��a cho b�n m�t cu�n s�ch k� n�ng ��i h�i k� n�ng ch�i brawler, b�n s� th�y n� r�t h�u �ch. B�n c�ng �� nh�n ���c th�m ch� cho c�c m�c s� d�ng, m�t th�c t� ��y ��. T�i c�ng �� t�ng MaxHP v� MaxMP c�a b�n. T� m�nh ki�m tra.");
    } else if (status == 38) {
	cm.sendNext("T�i �� ��a cho b�n m�t ch�t #bSP#k, v� v�y t�i �� ngh� b�n m� menu #bskill#k ngay b�y gi�.B�n s� c� th� n�ng cao k� n�ng l�m vi�c th� 2 c�a m�nh. H�y c�n th�n r�ng kh�ng ph�i t�t c� c�c k� n�ng ��u c� th� C� m�t s� k� n�ng m� b�n ch� c� th� c� ���c sau khi l�m ch� c�c k� n�ng c� b�n....");
    } else if (status == 39) {
	cm.sendNext("Brawlers c�n ph�i l� m�t l�c l��ng m�nh m�, nh�ng �i�u �� kh�ng c� ngh�a l� h� c� quy�n b�t n�t k� y�u. True Brawlers s� d�ng s�c m�nh to l�n c�a h� theo nh�ng c�ch t�ch c�c, �� l� kh� h�n l� ch� ��o t�o �� ��t ���c s�c m�nh. T�i hy v�ng b�n l�m theo Creed n�y khi b�n �� l�i d�u �n c�a b�n trong th� gi�i n�y nh� m�t Brawler. T�i s� g�p b�n khi b�n �� ho�n th�nh m�i th� b�n c� th� l�m Brawler. T�i s� ��i b�n � ��y.");
	cm.safeDispose();
    } else if (status == 40) {
	cm.sendNext("B�n mu�n tr� th�nh #bTh�n s�ng#k.");
    } else if (status == 41) {
	if (cm.getJob() == 500) {
	    cm.changeJob(520);
	    cm.gainSp(1);
	}
	cm.sendNext("T�t nhi�n b�y gi� b�n l� #bTh�n s�ng#k."); // Not complete
    } else if (status == 42) {
	cm.sendNext("T�i v�a ��a cho b�n m�t cu�n s�ch k� n�ng ��i h�i k� n�ng c�a Gunslinger, b�n s� th�y n� r�t h�u �ch. B�n c�ng �� nh�n ���c th�m ch� cho c�c m�c s� d�ng, m�t th�c t� ��y ��. T�i c�ng �� t�ng MaxHP v� MaxMP c�a b�n. T� m�nh ki�m tra.");
    } else if (status == 43) {
	cm.sendNext("T�i �� ��a cho b�n m�t ch�t #bSP#k, v� v�y t�i �� ngh� b�n m� menu #bskill#k ngay b�y gi�.B�n s� c� th� n�ng cao k� n�ng l�m vi�c th� 2 c�a m�nh. H�y c�n th�n r�ng kh�ng ph�i t�t c� c�c k� n�ng ��u c� th� C� m�t s� k� n�ng m� b�n ch� c� th� c� ���c sau khi l�m ch� c�c k� n�ng c� b�n....");
    } else if (status == 44) {
	cm.sendNext("I'll be waiting for you here."); // Not complete
	cm.safeDispose();
    } else if (status == 45) {
	cm.sendNext("Okay, as promised, you will now become a #bCannoneer#k.");
    } else if (status == 46) {
	if (cm.getJob() == 501) {
	    cm.changeJob(530);
	}
	cm.sendNext("Okay, from here on out, you are a #bCannoneer#k."); // Not complete
    } else if (status == 47) {
	cm.sendNext("I have just given you a skill book that entails Cannoneer skills, you'll find it very helpful. You have also gained additional slots for Use items, a full row in fact. I also boosted your MaxHP and MaxMP. Check it out for yourself.");
    } else if (status == 48) {
	cm.sendNext("I have given you a little bit of #bSP#k, so I suggest you open the #bskill menu#k right now. You'll be able to enhance your newly-acquired 2nd Job skills. Beware that not all skills can be enhanced from the get go. There are some skills that you can only acquire after mastering basic skills.");
    } else if (status == 49) {
	cm.sendNext("I'll be waiting for you here."); // Not complete
	cm.safeDispose();
    } else if (status == 100) {
	if (cm.getQuestStatus(6370) == 1) { // Captain
	    var dd = cm.getEventManager("KyrinTrainingGroundC");
	    if (dd != null) {
		dd.startInstance(cm.getPlayer());
	    } else {
		cm.sendOk("An unknown error occured.");
	    }
	} else if (cm.getQuestStatus(6330) == 1) { // Viper
	    var dd = cm.getEventManager("KyrinTrainingGroundV");
	    if (dd != null) {
		dd.startInstance(cm.getPlayer());
	    } else {
		cm.sendOk("An unknown error occured.");
	    }
	}
	cm.dispose();
    } else if (status == 101) {
	if (cm.getQuestStatus(6370) == 1) { // Captain
	    cm.teachSkill(5221006, 0, 10);
//	    cm.forceCompleteQuest(6371);
	    cm.forceCompleteQuest(6370);
	} else if (cm.getQuestStatus(6330) == 1) { // Viper
	    cm.teachSkill(5121003, 0, 10);
//	    cm.forceCompleteQuest(6331);
	    cm.forceCompleteQuest(6330);
	}
	cm.warp(120000101, 0);
	cm.dispose();
    }
}