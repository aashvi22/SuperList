import java.util.Collections;

public class SuperListRunner{

	public static void main(String[] args) {

		//1) making an arraylist
		System.out.println("PART 1");
		SuperList<Integer> arraylist = new SuperList<Integer>();
		for(int i = 0; i < 30; i++) {
			arraylist.add((int)(Math.random()*1000)+1);
		}
		System.out.println("arraylist "+arraylist);
		System.out.println("arraylist size "+arraylist.size);
		
		//2) making a stack
		System.out.println("PART 2 (this is where I make a stack, which is printed in part 3)");
		SuperList<Integer> stack = new SuperList<Integer>();
		for(int i = 0; i < 30; i++) {
			stack.push(arraylist.remove(0));
		}
		//3) making a queue and printing out the stack
		System.out.println("PART 3");
		SuperList<Integer> queue = new SuperList<Integer>();
		String st="[";
		for(int i = 0; i < 30; i++) {
			int value = stack.pop();
			st+=value;
			if(i<29) {
				st+=", ";
			}
			queue.add(value);
		}
		st+="]";
		System.out.println("stack "+st);
		System.out.println("queue "+queue);
		
		//4) filling queue into random indices in arraylist
		System.out.println("PART 4");
		for(int i = 0; i < 30;i++) {
			arraylist.add(0);
		}
		SuperList<Integer> prevIndices=new SuperList<Integer>();
		
		for(int i = 0; i < 30; i++) {
			int num = (int)(Math.random()*30);
			while(prevIndices.contains(num)) {
				num = (int)(Math.random()*30);
			}
			arraylist.remove(num);
			arraylist.add(num, queue.poll());
			prevIndices.add(num);
		}
		System.out.println("shuffled arraylist "+arraylist);
		
		//5) taking the sums of the arraylist
		System.out.println("PART 5");
		int sum = 0;
		int evensum=0;
		int oddsum=0;
		for(int i = 0; i < 30; i++) {
			sum+=arraylist.get(i);
			if(i%2==0) {
				evensum+=arraylist.get(i);
				arraylist.add(arraylist.get(i));
			}
			if(i%2!=0) 
				oddsum+=arraylist.get(i);
		}
		System.out.println("(odd sum) " + oddsum + " + (even sum) " + evensum + " = (total sum) " + sum);
		
		//6) removing numbers divisible by 3
		System.out.println("PART 6");
		for(int i = 0; i < arraylist.size; i++) {
			if(arraylist.get(i)%3==0) {
				arraylist.remove(i);
			}
		}
		
		//7) adding 55555 to position 4
		System.out.println("PART 7");
		arraylist.add(4,55555);
		System.out.println("arraylist with 55555 "+arraylist);
		
		//8) sorting the arraylist
		System.out.println("PART 8");
		System.out.println("sorted arraylist "+sortInt(arraylist));
		
		//9) finding the median, listing before and after
		System.out.println("PART 9");
		double median;
		if(arraylist.size%2==0)
			median = (arraylist.get(arraylist.size/2)+arraylist.get(arraylist.size/2+1))/2.0;
		else
			median = arraylist.get(arraylist.size/2+1);
		SuperList<Integer> beforemedian = new SuperList<Integer>();
		SuperList<Integer> aftermedian = new SuperList<Integer>();
		for(int i = 0; i < arraylist.size; i++) {
			if(arraylist.size%2==0) {
				if(i<=arraylist.size/2) {
					beforemedian.add(arraylist.get(i));
				}
				else {
					aftermedian.add(arraylist.get(i));
				}
			}
			else{
				if(i<arraylist.size/2+1) {
					beforemedian.add(arraylist.get(i));
				}
				if(i>arraylist.size/2+1) {
					aftermedian.add(arraylist.get(i));
				}
			}
		}
		System.out.println("median is " +median + " for size " + arraylist.size);
		System.out.println("numbers before median " + beforemedian);
		System.out.println("numbers after median " + aftermedian);
		
		//10) new string superlist, store the words in a sentence
		System.out.println("PART 10");
		SuperList<String> stringlist = new SuperList<String>();
		String sentence=  "The quick brown fox jumped over the lazy dog";
		String[]pieces=sentence.split(" ");
		for(int i = 0; i < pieces.length;i++)
			stringlist.add(pieces[i]);
		System.out.println("String list (original) " + stringlist);
		
		//11) remove words with less than three characters
		System.out.println("PART 11");
		for(int i = 0; i < stringlist.size;i++) {
			if(stringlist.get(i).length()<=3)
				stringlist.remove(i);
		}
		System.out.println("String list (removed words) " + stringlist);
		
		//12) sort words (by length)
		System.out.println("PART 12");
		System.out.println("words sorted by length " + sortString(stringlist));
		
		//13) average word length
		System.out.println("PART 13");
		int totlength=0;
		for(int i = 0; i < stringlist.size;i++) {
			totlength+=stringlist.get(i).length();
		}
		System.out.println("avg word length " + totlength/(stringlist.size*1.0));
	}
	public static SuperList<Integer> sortInt(SuperList<Integer> list){
		int n = list.size;  
        for (int j = 1; j < n; j++) {  
            int key = list.get(j);  
            int i = j-1;  
            while ( (i >= 0) && ( list.get(i) > key ) ) {  
            		list.remove(i+1);  
            		list.add(i+1,list.get(i));  
                i--;  
            }
            list.remove(i+1);
            list.add(i+1,key);  
        }  
        return list;
	}
	public static SuperList<String> sortString(SuperList<String> list){
		int n = list.size;  
        for (int j = 1; j < n; j++) {  
            String key = list.get(j);  
            int i = j-1;  
            while ( (i > -1) && ( list.get(i).length() > key.length() ) ) {  
            		list.remove(i+1);
            		list.add(i+1,list.get(i));  
                i--;  
            }  
            list.remove(i+1);
            list.add(i+1,key);  
        }  
        return list;
	}

}

 
