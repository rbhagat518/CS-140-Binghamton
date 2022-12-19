import java.io.*;
import java.nio.*;

class bhagat_p2 {
	public static void main(String[] args) {
		// put some code here to check for three commandline arguments
		if (args.length < 3) {
			System.out.println("Requires three commandline arguments");
			System.exit(0);
		}

		// puts some code here to check that the first commandline argument starts with
		// "b" or "t"

		if (args[0].startsWith("b")) {
			convertBinaryToText(args[1], args[2]);
		} else if (args[0].startsWith("t")) {
			convertTextToBinary(args[1], args[2]);
		} else {
			System.out.println(
					"First argument was incorrect. It must start with a b or a t. B for binary to text and t for text to binary");
			System.exit(0);

		}
	}

	private static void convertBinaryToText(String inputFilename, String outputFilename) {

		System.out.println("convertBinaryToText");
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFilename));
			PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			byte[] buffer = new byte[input.available()];
			int bufferIndex = 0;
			int nextByte = 0;
			while ((nextByte = input.read()) >= 0) {
				buffer[bufferIndex] = (byte) nextByte;
				bufferIndex++;
			}
			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
			int bufferLength = byteBuffer.remaining();
			int index = 4;
			while (index < bufferLength) {
				char type = byteBuffer.getChar(index);
				String strType = Character.toString(type);
				index += 2;
				if (strType.equals("i")) {
					output.write("int\t" + String.valueOf(byteBuffer.getInt(index)) + "\n");
					index += 4;
				}
				if (strType.equals("l")) {
					output.write("long\t" + String.valueOf(byteBuffer.getLong(index)) + "\n");
					index += 8;
				}
				if (strType.equals("h")) {
					output.write("short\t" + String.valueOf(byteBuffer.getShort(index)) + "\n");
					index += 2;
				}
				if (strType.equals("f")) {
					output.write("float\t" + String.valueOf(byteBuffer.getFloat(index)) + "\n");
					index += 4;
				}
				if (strType.equals("d")) {
					output.write("double\t" + String.valueOf(byteBuffer.getDouble(index)) + "\n");
					index += 8;
				}
				if (strType.equals("a")) {
					int size = byteBuffer.getInt(index);
					index += 4;
					output.write("int array\t");
					for (int s = 0; s < size; s++) {
						output.write(String.valueOf(byteBuffer.getInt(index)));
						index += 4;
						if (s != size - 1) {
							output.write(",");
						}
					}
					output.write("\n");
				}
				if (strType.equals("s")) {
					int size = byteBuffer.getInt(index);
					index += 4;
					output.write("string\t");
					for (int s = 0; s < size; s++) {
						output.write(String.valueOf(byteBuffer.getChar(index)));
						index += 2;
					}
					output.write("\n");
				}
				if (strType.equals("e")) {
					int size = byteBuffer.getInt(index);
					index += 4;
					output.write("double array\t");
					for (int s = 0; s < size; s++) {
						output.write(String.valueOf(byteBuffer.getDouble(index)));
						index += 8;
						if (s != size - 1) {
							output.write(",");
						}
					}
					output.write("\n");
				}
			}
			input.close();
			output.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
	}

	private static void convertTextToBinary(String inputFilename, String outputFilename) {

		System.out.println("convertTextToBinary");
		try {
			// put your code to read a text file and output it as a binary file
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
			DataOutputStream output = new DataOutputStream(new FileOutputStream(outputFilename));
			java.util.ArrayList<String> inputLines = new java.util.ArrayList<>();
			String inn;
			// int byteCount = 0;
			while ((inn = input.readLine()) != null) {
				inputLines.add(inn);
			}
			System.out.println(inputLines.size());
			output.writeInt(inputLines.size());

			for (int i = 0; i < inputLines.size(); i++) {
				String line = inputLines.get(i);
				String[] tokens = line.split("	");
				String type = tokens[0];
				String data = tokens[1];
				System.out.println(type + "\n" + "data " + data);
				// output.write(type.getBytes());
				if (type.contains("array")) {

					String[] arr_data = data.split(",");

					if (type.contains("double")) {
						output.writeChars("e");
						double[] arr = new double[arr_data.length];
						output.writeInt(arr.length);
						for (int j = 0; j < arr_data.length; j++) {
							arr[j] = Double.parseDouble(arr_data[j]);
							output.writeDouble(arr[j]);
						}
					}
					if (type.contains("int")) {
						output.writeChars("a");
						int[] arr = new int[arr_data.length];
						output.writeInt(arr.length);
						for (int j = 0; j < arr_data.length; j++) {
							arr[j] = Integer.parseInt(arr_data[j]);
							System.out.println(arr[j]);
							output.writeInt(arr[j]);
						}
					}

				} else {
					System.out.println("Not an array type");

					String[] norm_data = data.split(" ");

					if (type.equals("int")) {
						output.writeChars("i");
						for (String x : norm_data) {
							int temp = Integer.parseInt(x);
							output.writeInt(temp);
						}

					}
					if (type.equals("double")) {
						output.writeChars("d");
						for (String x : norm_data) {
							double temp = Double.parseDouble(x);
							output.writeDouble(temp);
						}

					}
					if (type.equals("float")) {
						output.writeChars("f");
						for (String x : norm_data) {
							float temp = Float.parseFloat(x);
							output.writeFloat(temp);
						}

					}
					if (type.equals("short")) {
						output.writeChars("h");
						for (String x : norm_data) {
							short temp = Short.parseShort(x);
							output.writeShort(temp);
						}

					}
					if (type.equals("long")) {
						output.writeChars("l");
						for (String x : norm_data) {
							long temp = Long.parseLong(x);
							output.writeLong(temp);
						}
					}

					if (type.equals("string")) {
						System.out.println("String type");
						output.writeChars("s");
						int chars = 0;
						chars += data.length();
						output.writeInt(chars);
						for (int j = 0; j < norm_data.length; j++) {
							output.writeChars(norm_data[j]);
							if (j != norm_data.length - 1) {
								output.writeChars(" ");
							}
						}
					}
				}

				/*
				 * 
				 * int arrSize = arrayTokenizer.countTokens();
				 * String type = st.nextToken();
				 * output.write(type.getBytes());
				 * if (type.equals("int")) {
				 * while(st.hasMoreTokens())
				 * {
				 * String arr_test = st.nextToken();
				 * if(arr_test.equals("array"))
				 * {
				 * 
				 * int[] arr = new int[arrSize];
				 * int count = 0;
				 * output.write(a.getBytes());
				 * while (arrayTokenizer.hasMoreTokens()) {
				 * arr[count] = Integer.parseInt(arrayTokenizer.nextToken());
				 * output.writeInt(arr[count]);
				 * count++;
				 * }
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * } else if (type.equals("double")) {
				 * double[] arr = new double[arrSize];
				 * int count = 0;
				 * output.write(type.getBytes());
				 * while (arrayTokenizer.hasMoreTokens()) {
				 * arr[count] = Double.parseDouble(arrayTokenizer.nextToken());
				 * output.writeDouble(arr[count]);
				 * count++;
				 * }
				 * 
				 * } else if (type.equals("int")) {
				 * 
				 * } else if (type.equals("double")) {
				 * 
				 * } else if (type.equals("String")) {
				 * 
				 * }
				 */
				input.close();
				output.close();

			}

			// PrintWriter.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
