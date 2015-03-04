/**
 * SimilarImageSearch Class
 *
 * @author ZhihengYi, Wenji Yang - UOttawa
 * @version V1
 * @since Feb 2015
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SimilarImageSearch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<String> hashCodes = new ArrayList<String>();

        String filename = ImageHelper.path + "/images/";
        String hashCode = null;
        System.out.println("Source: ------------------------------------------------------");
        String sourceHashCode = produceFingerPrint(filename + "source.jpg");

        //System.out.println(sourceHashCode);
        System.out.println();

        System.out.println("Examples: ------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            hashCode = produceFingerPrint(filename + "example" + (i + 1) + ".jpg");
            hashCodes.add(hashCode);
        }

        //System.out.println(hashCodes);
        System.out.println();


        List<Integer> differences = new ArrayList<Integer>();
        for (int i = 0; i < hashCodes.size(); i++) {
            int difference = hammingDistance(sourceHashCode, hashCodes.get(i));
            differences.add(difference);
        }
        System.out.println("Hamming Distance Result: ");
        System.out.println(differences);

    }

    /**
     * Calculate Hamming distance
     * If the distance is less than or equal to 10, which means two images are very similar.
     *
     * @param sourceHashCode source image fingerprint
     * @param hashCode       example image fingerprint
     */
    public static int hammingDistance(String sourceHashCode, String hashCode) {
        int difference = 0;
        int len = sourceHashCode.length();

        for (int i = 0; i < len; i++) {
            if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
                difference++;
            }
        }

        return difference;
    }

    /**
     * Produce image fingerprint
     *
     * @param filename the image filename we need to analyse
     * @return image fingerprint
     */
    public static String produceFingerPrint(String filename) {
        BufferedImage source = ImageHelper.readPNGImage(filename);// 读取文件

        int width = 8;
        int height = 8;

        // Step 1, Reduce Size
        // Shrink the image to 8*8 pixels.
        // The effect of this step vanishes the detail of the image and keeps the basic information such as structure and brightness.
        BufferedImage thumb = ImageHelper.thumb(filename, source, width, height, false);

        // Step 2, Simplify Color
        // Make every shrunk picture to 64 grayscale level, which means transforming every pixel to 64 total colors.
        int[] pixels = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i * height + j] = ImageHelper.rgbToGray(thumb.getRGB(i, j));
            }
        }

        // Step 3, calculate average value
        // Calculate the average grayscale value for all 64 pixels.
        int avgPixel = ImageHelper.average(pixels);
        System.out.println("Average Greyscale: " + avgPixel);

        // Step 4, Compare Grayscale
        // Compare the greyscale of every pixel with the average value.
        // If the result is greater or equal to 0, we make this pixel value to 1. Otherwise, make the value to 0.

        int[] comps = new int[width * height];
        for (int i = 0; i < comps.length; i++) {
            if (pixels[i] >= avgPixel) {
                comps[i] = 1;
            } else {
                comps[i] = 0;
            }
        }

        // Step 5, Calculate the Hash
        StringBuffer hashCode = new StringBuffer();
        for (int i = 0; i < comps.length; i+= 4) {
            int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1] * (int) Math.pow(2, 2) + comps[i + 2] * (int) Math.pow(2, 1) + comps[i + 3];
            hashCode.append(binaryToHex(result));
        }

        String stringValue = "";
        for (int i = 0; i < comps.length; i++) {
            stringValue += comps[i];
        }
        System.out.println("Image Fingerprint: " + hashCode.toString());
        System.out.println();
        return stringValue;

    }
    private static char binaryToHex(int binary) {
        char ch = ' ';
        switch (binary)
        {
            case 0:
                ch = '0';
                break;
            case 1:
                ch = '1';
                break;
            case 2:
                ch = '2';
                break;
            case 3:
                ch = '3';
                break;
            case 4:
                ch = '4';
                break;
            case 5:
                ch = '5';
                break;
            case 6:
                ch = '6';
                break;
            case 7:
                ch = '7';
                break;
            case 8:
                ch = '8';
                break;
            case 9:
                ch = '9';
                break;
            case 10:
                ch = 'a';
                break;
            case 11:
                ch = 'b';
                break;
            case 12:
                ch = 'c';
                break;
            case 13:
                ch = 'd';
                break;
            case 14:
                ch = 'e';
                break;
            case 15:
                ch = 'f';
                break;
            default:
                ch = ' ';
        }
        return ch;
    }

}
