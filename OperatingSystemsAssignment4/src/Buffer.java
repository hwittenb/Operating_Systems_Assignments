
public class Buffer {
	private char[] arr;
	private int inputIndex;
	private int outputIndex;
	
	public Buffer(int size) {
		arr = new char[size];
		inputIndex = 0;
		outputIndex = 0;
	}
	
	public void addElement(char c) {
		arr[inputIndex] = c;
		if(inputIndex == (arr.length - 1)) {
			inputIndex = 0;
		}
		else {
			inputIndex++;
		}
	}
	
	public char getElement() {
		char c = arr[outputIndex];
		if(outputIndex == arr.length - 1){
			outputIndex = 0;
		}
		else {
			outputIndex++;
		}
		return c;
	}
}
