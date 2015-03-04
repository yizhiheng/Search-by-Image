# Search by Image
A basic Java implementation of "Search by Image"

In this project, we use an algorithm called ‘Perceptual Hash Algorithms’, which works for creating a ‘fingerprint’ --- unique character string of every image and compare every fingerprint with the original image. The closer the result is, the more similar two images are.

## Perceptual Hash Algorithm
### Step 1. Reduce Size
Shrink the image to 8x8 pixels (total sum is 64 pixels). The effect of this step vanishes the detail of the image and keeps the basic information such like structure and brightness. What’s more, it can also remove the difference of the different size or scale.
### Step 2. Simplify Color
Make ever shrunk picture to 64 grayscale level which means transforming every pixel to 64 total colors.
### Step 3. Average Grayscale
Calculate the average grayscale value for all 64 pixels.
### Step4. Compare Pixel Grayscale
Compare the grayscale of every pixel with the average value. If the result is greater or equal to 0, we make this pixel value to 1. Otherwise, make the vale to 0.
### Step 5. Compute Hash
After last step, we can obtain 64 bits with 0 or 1 integer string. Reconstruct the 64 bits to a new image with identical order. The 64 integer is the fingerprint for every image.
### Step 6. Compare Fingerprint
If we want to find the similar image, what we are supposed to do is comparing the fingerprint difference of the image with original image. Find how many distinct bits which is called Hamming Distance. In theory, if the distinct positions are below 5, we can tell that two images are similar. If the distinct positions are above 10, it states that the two images are different.

## References
http://www.hackerfactor.com/blog/index.php?/archives/432-Looks-Like-It.html

Enjoy!!
