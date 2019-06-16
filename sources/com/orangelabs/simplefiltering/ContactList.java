package com.orangelabs.simplefiltering;

import android.support.v4.media.TransportMediator;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class ContactList {
    public static volatile transient /* synthetic */ IncrementalChange $change;
    public String[] contactList;

    ContactList(Object[] objArr, InstantReloadException instantReloadException) {
        switch (((String) objArr[0]).hashCode()) {
            case -1968665286:
                return;
            case 1535301921:
                this();
                return;
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{(String) objArr[0], Integer.valueOf(((String) objArr[0]).hashCode()), "com/orangelabs/simplefiltering/ContactList"}));
        }
    }

    public static /* synthetic */ Object access$super(ContactList contactList, String str, Object... objArr) {
        switch (str.hashCode()) {
            case -2128160755:
                return super.toString();
            case -1600833221:
                super.wait(((Number) objArr[0]).longValue(), ((Number) objArr[1]).intValue());
                return null;
            case -1554832987:
                super.finalize();
                return null;
            case -1166127280:
                super.notify();
                return null;
            case -1021472056:
                super.wait(((Number) objArr[0]).longValue());
                return null;
            case -712101345:
                super.notifyAll();
                return null;
            case 201261558:
                return super.getClass();
            case 244142972:
                super.wait();
                return null;
            case 1403628309:
                return new Integer(super.hashCode());
            case 1814730534:
                return new Boolean(super.equals(objArr[0]));
            case 2025021518:
                return super.clone();
            default:
                throw new InstantReloadException(String.format("String switch could not find '%s' with hashcode %s in %s", new Object[]{str, Integer.valueOf(str.hashCode()), "com/orangelabs/simplefiltering/ContactList"}));
        }
    }

    public String[] getContactList() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange == null) {
            return this.contactList;
        }
        return (String[]) incrementalChange.access$dispatch("getContactList.()[Ljava/lang/String;", new Object[]{this});
    }

    public ContactList() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            this((Object[]) incrementalChange.access$dispatch("init$args.([Ljava/lang/Object;)Ljava/lang/Object;", new Object[]{r2}), null);
        }
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("init$body.(Lcom/orangelabs/simplefiltering/ContactList;)V", new Object[]{this});
            return;
        }
        String[] strArr = new String[TransportMediator.KEYCODE_MEDIA_RECORD];
        strArr[0] = "Andre";
        strArr[1] = "Arnaud";
        strArr[2] = "Aubert";
        strArr[3] = "Adam";
        strArr[4] = "Aubry";
        strArr[5] = "Bernard";
        strArr[6] = "Bertrand";
        strArr[7] = "Bonnet";
        strArr[8] = "Boyer";
        strArr[9] = "Blanc";
        strArr[10] = "Chevalier";
        strArr[11] = "Clement";
        strArr[12] = "Caron";
        strArr[13] = "Colin";
        strArr[14] = "Charpentier";
        strArr[15] = "Dubois";
        strArr[16] = "Durand";
        strArr[17] = "David";
        strArr[18] = "Dupont";
        strArr[19] = "Dumont";
        strArr[20] = "Etienne";
        strArr[21] = "Evrard";
        strArr[22] = "Estelle";
        strArr[23] = "Elisabeth";
        strArr[24] = "Esnault";
        strArr[25] = "Fournier";
        strArr[26] = "Francois";
        strArr[27] = "Fontaine";
        strArr[28] = "Fabre";
        strArr[29] = "Fernandez";
        strArr[30] = "Garnier";
        strArr[31] = "Garcia";
        strArr[32] = "Girard";
        strArr[33] = "Guerin";
        strArr[34] = "Gauthier";
        strArr[35] = "Henry";
        strArr[36] = "Hubert";
        strArr[37] = "Herve";
        strArr[38] = "Huet";
        strArr[39] = "Humbert";
        strArr[40] = "Imbert";
        strArr[41] = "Ibanez";
        strArr[42] = "Inconu";
        strArr[43] = "Idrissi";
        strArr[44] = "Isnard";
        strArr[45] = "Joly";
        strArr[46] = "Jean";
        strArr[47] = "Jacquet";
        strArr[48] = "Jacob";
        strArr[49] = "Joubert";
        strArr[50] = "Klein";
        strArr[51] = "Kieffer";
        strArr[52] = "Kuhn";
        strArr[53] = "Koch";
        strArr[54] = "Kevin";
        strArr[55] = "Leroy";
        strArr[56] = "Laurent";
        strArr[57] = "Lefebvre";
        strArr[58] = "Legrand";
        strArr[59] = "Lambert";
        strArr[60] = "Martin";
        strArr[61] = "Moreau";
        strArr[62] = "Michel";
        strArr[63] = "Morel";
        strArr[64] = "Mercier";
        strArr[65] = "Nicolas";
        strArr[66] = "Nguyen";
        strArr[67] = "Noel";
        strArr[68] = "Normand";
        strArr[69] = "Navarro";
        strArr[70] = "Olivier";
        strArr[71] = "Ollivier";
        strArr[72] = "Oliveira";
        strArr[73] = "Ortega";
        strArr[74] = "Oger";
        strArr[75] = "Petit";
        strArr[76] = "Perrin";
        strArr[77] = "Pierre";
        strArr[78] = "Perez";
        strArr[79] = "Philippe";
        strArr[80] = "Quentin";
        strArr[81] = "Quere";
        strArr[82] = "Quemener";
        strArr[83] = "Quillet";
        strArr[84] = "Quintard";
        strArr[85] = "Robert";
        strArr[86] = "Roux";
        strArr[87] = "Rousseau";
        strArr[88] = "Roussel";
        strArr[89] = "Robin";
        strArr[90] = "Simon";
        strArr[91] = "Schmitt";
        strArr[92] = "Sanchez";
        strArr[93] = "Sauvage";
        strArr[94] = "Smith";
        strArr[95] = "Thomas";
        strArr[96] = "Tessier";
        strArr[97] = "Thibault";
        strArr[98] = "Texier";
        strArr[99] = "Thierry";
        strArr[100] = "Urbain";
        strArr[101] = "Uzan";
        strArr[102] = "Uguen";
        strArr[103] = "Ulrich";
        strArr[104] = "Urban";
        strArr[105] = "Vidal";
        strArr[106] = "Vasseur";
        strArr[107] = "Verdier";
        strArr[108] = "Vallee";
        strArr[109] = "Voisin";
        strArr[110] = "Weber";
        strArr[111] = "Wagner";
        strArr[112] = "Walter";
        strArr[113] = "Weiss";
        strArr[114] = "Wolff";
        strArr[115] = "Xavier";
        strArr[116] = "Xiong";
        strArr[117] = "Xech";
        strArr[118] = "Xena";
        strArr[119] = "Xerri";
        strArr[120] = "Yves";
        strArr[121] = "Yang";
        strArr[122] = "Yann";
        strArr[123] = "Yahiaoui";
        strArr[124] = "Youssef";
        strArr[125] = "Zimmermann";
        strArr[TransportMediator.KEYCODE_MEDIA_PLAY] = "Zimmer";
        strArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = "Zaoui";
        strArr[128] = "Zerbib";
        strArr[129] = "Ziani";
        this.contactList = strArr;
    }
}
