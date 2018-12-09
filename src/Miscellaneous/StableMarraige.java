/*package Array;
// https://www.geeksforgeeks.org/stable-marriage-problem/
// Gale Shapley Algorithm

public class StableMarraige {

	private int N, engagedCount;
	private String[][] menPref;
	private String[][] womenPref;
	private String[] men;
	private String[] women;
	private String[] womenPartner;
	private boolean[] menEngaged;
	 
	public StableMarraige(String[] m, String[] w, String[][] mp, String[][] wp) {
		N = mp.length;
		engagedCount = 0;
		men = m;
		women = w;
		menPref = mp;
		womenPref = wp;
		menEngaged = new boolean[N];
		womenPartner = new String[N];
	}

	private  void doPerfectMatches() {
        
		while( engagedCount < N ) {
			int free;
			for(free =0; free<N ; free++) {
				if(!menEngaged[free]) 
					break;
			}
			for(int i=0; i<N; i++) {
				String prefemaleIndex =  menPref[free][i];                   // pick the best available choices of men pref list
				int women_index = getPartnerIndex(prefemaleIndex, women);         // getting index of female partner
				if(womenPartner[women_index] == null) {                                // checking that partner is free or paired up
					
					womenPartner[women_index] = men[free];                             // pairing 
					menEngaged[free]= true;
					engagedCount++;
					break;
					
				}else {
					String previouschoiceMen = womenPartner[women_index];                           
					if(isBetterChoice(previouschoiceMen, women_index, men[free])) {             
						womenPartner[women_index] = men[free];                                      // pair up with new partner
						menEngaged[free] = true;
						menEngaged[getPartnerIndex(previouschoiceMen, men)]= false;   // break up 
						break;
					}
				}
			}	
		}
		printPairs();
	}

	private void printPairs() {
		for (int i = 0; i < N; i++) {
			System.out.println(womenPartner[i] + " engaged to : " + women[i]);
		}
	}

	private int getPartnerIndex(String preference, String[] partner) {
		for (int k = 0; k < N; k++) {
			if (partner[k].equals(preference)) 
				return k;
		}
		return -1;
	}

	private boolean isBetterChoice(String previouschoiceMen, int womenIndex, String freemale) {
		for (int i = 0; i < N; i++) {
			if (womenPref[womenIndex][i].equals(freemale)) {
				return true;
			}
			if (womenPref[womenIndex][i].equals(previouschoiceMen)) {
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		
        *//** list of men **//*
        String[] m = {"M1", "M2", "M3", "M4", "M5"};
        *//** list of women **//*
        String[] w = {"W1", "W2", "W3", "W4", "W5"};
 
        *//** men preference list**//*
        String[][] mp = {{"W5", "W2", "W3", "W4", "W1"}, 
                         {"W2", "W5", "W1", "W3", "W4"}, 
                         {"W4", "W3", "W2", "W1", "W5"}, 
                         {"W1", "W2", "W3", "W4", "W5"},
                         {"W5", "W2", "W3", "W4", "W1"}};
        
        *//** women preference list **//*                      
        String[][] wp = {{"M5", "M3", "M4", "M1", "M2"}, 
                         {"M1", "M2", "M3", "M5", "M4"}, 
                         {"M4", "M5", "M3", "M2", "M1"},
                         {"M5", "M2", "M1", "M4", "M3"}, 
                         {"M2", "M1", "M4", "M3", "M5"}};
 
        StableMarraige sm= new StableMarraige(m, w, mp, wp);;    
        sm.doPerfectMatches();
	}

}
*/