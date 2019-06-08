package sorts;

import static array.visualizer.ArrayVisualizer.clearmarked;
import static array.visualizer.ArrayVisualizer.compare;
import static array.visualizer.ArrayVisualizer.marked;
import static array.visualizer.ArrayVisualizer.sleep;
import static array.visualizer.Writes.swap;

/*
MIT License

Copyright (c) 2019 w0rthy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public class BogoSorts {
	private static boolean bogoIsSorted(int[] i, int length){
		for(int x = 0; x < length - 1; x++) {
			if(compare(i[x], i[x+1]) == 1) {
				marked.set(1, x);
				return false;
			}
		}
		marked.set(1, i.length-1);
		return true;
	}
    
    public static void bogoSort(int[] i, int length){
        while(!bogoIsSorted(i, length)){
        	for(int j = 0; j < length; ++j) {
        		int index1 = (int) (Math.random() * length),
        			index2 = (int) (Math.random() * length);
        			swap(i, index1, index2, 0, false);
            }
        }
        clearmarked();
    }
    
private static int iterator;
	
	public static void lessBogoSort(int[] a, int length) {
    	iterator = 0;
    	
    	while(iterator != length) {
    		while(!isZeroSorted(a, length)) {
    			bogoSwap(a, length);
    		}
    		marked.set(1, iterator);
    		iterator++;
    	}
    	
    	marked.set(1, -5);
    	marked.set(2, -5);
    	marked.set(3, -5);
    }
	
	private static void bogoSwap(int[] a, int length){
		for(int i = iterator; i < length; i++) {
            swap(a, i, randomPosition(a, length), 0, true);
        }
    }
	
	private static int randomPosition(int[] a, int length) {
		int value = (int) ((Math.random() * length) + iterator);

		return Math.min(value, length-1);
	}
	
	private static boolean isZeroSorted(int[] a, int length) {
    	clearmarked();
    	marked.set(2, iterator);
    	for(int j = iterator + 1; j < length; j++) {
        	marked.set(1, j);
        	sleep(75);
        	if(compare(a[iterator], a[j]) == 1) {
                return false;
            }
        }
    	return true;
    }
	
    private static int minIterator;
	private static int maxIterator;
	
	public static void doubleBogoSort(int[] a, int length) throws Exception {
    	minIterator = 0;
    	maxIterator = length - 1;
    	
    	while(minIterator < maxIterator) {
        	boolean maxSorted = isMaxSorted(a);
        	boolean minSorted = isMinSorted(a);
        	
        	while( !maxSorted && !minSorted ) {
    			doubleBogoSwap(a);
    			
    			maxSorted = isMaxSorted(a);
    	    	minSorted = isMinSorted(a);
    		}
    		
    		if(minSorted) {
            	marked.set(1, minIterator);
            	minIterator++;
            	minSorted = false;
            }
    		if(maxSorted) {
            	marked.set(1, maxIterator);
            	maxIterator--;
            	maxSorted = false;
        	}
    	}
    	clearmarked();
    }
	
	private static void doubleBogoSwap(int[] a){
		for(int i = minIterator; i <= maxIterator; i++) {
            swap(a, i, doubleRandomPosition(a), 0, true);
        }
    }
	
	private static int doubleRandomPosition(int[] a) {
		int value = (int) ((Math.random() * maxIterator) + minIterator);

		return Math.min(value, maxIterator);
	}
	
	private static boolean isMinSorted(int[] a) {
		clearmarked();
		marked.set(2, minIterator);
		marked.set(3, maxIterator);
    	for(int j = minIterator + 1; j <= maxIterator; j++) {
        	marked.set(1, j);
        	sleep(75);
        	if(compare(a[minIterator], a[j]) == 1) {
        		return false;
            }
        }
    	return true;
    }
	
	private static boolean isMaxSorted(int[] a) {
    	clearmarked();
		marked.set(2, minIterator);
		marked.set(3, maxIterator);
		for(int j = maxIterator - 1; j >= minIterator; j--) {
        	marked.set(1, j);
        	sleep(75);
        	if(compare(a[maxIterator], a[j]) == -1) {
                return false;
            }
        }
    	return true;
    }

}