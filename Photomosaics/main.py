import numpy as np
from PIL import Image
import math
import os


def get_pixel_matrix(image):
    """
    Converts a image to 2D pixel matrix
    Args:
        img -- PIL object
    Returns:
        2D pixel matrix
    """
    pixels = list(image.getdata())
    pixel_matrix = []
    for i in range(0, len(list(pixels)), image.width):
        raw = []
        for j in range(image.width):
            raw.append(pixels[i + j])
        pixel_matrix.append(raw)
    return pixel_matrix


def get_divide(image, n):
    """
    Divides a matrix into nxn subsquares and averages each subsquares
    Args:
        img -- PIL object
        n -- integer that one edge of subsquares
    Returns:
        2D matrix of average color subsquares
    """
    pixel_matrix = get_pixel_matrix(image)
    empty = []
    for i in range(0, len(pixel_matrix), n):
        temp = []
        for j in range(0, len(pixel_matrix), n):
            red = 0
            green = 0
            blue = 0
            for k in range(n):
                for l in range(n):
                    red += pixel_matrix[i + k][j + l][0]
                    green += pixel_matrix[i + k][j + l][1]
                    blue += pixel_matrix[i + k][j + l][2]
            temp.append((red/(n*n), green/(n*n), blue/(n*n)))
        empty.append(temp)
    return empty


def pixelate(image, n):
    """
    Takes an image and divides it into subsquares and reconstruct input image according to average color of subsquares
    Args:
         img -- PIL object
          n -- integer that one edge of subsquares
    Returns:
        image -- PIL object
    """
    pixels = get_pixel_matrix(image)
    new_pixels = get_divide(image, n)
    new_image_data = []

    # it looks pretty shitty but its n^2
    a = 0
    for i in range(0, len(pixels), n):
        b = 0
        for j in range(0, len(pixels), n):
            for k in range(n):
                for l in range(n):
                    pixels[i + k][j + l] = new_pixels[a][b]
            b += 1
        a += 1
    for i in range(len(pixels)):
        for j in range(len(pixels)):
            new_image_data.append(pixels[i][j])

    # to make new image
    # convert pixels matrix to array
    array = np.array(pixels, dtype=np.uint8)
    new_image = Image.fromarray(array)
    return new_image


def average_color_of_image(dir):
    """
    takes average color of all images that in source directory
    Args:
        directory path
    Returns:
        an array that contains average color data of source images
    """
    images = []
    for filename in os.listdir(dir):
        if filename.endswith(".jpg"):
            full_src_path = os.path.join(dir, filename)
            source = Image.open(full_src_path)
            pixels = list(source.getdata())
            r = 0
            g = 0
            b = 0
            for pixel in pixels:
                r += pixel[0]
                g += pixel[1]
                b += pixel[2]
            r /= len(pixels)
            g /= len(pixels)
            b /= len(pixels)

            avarage_color = (r, g, b)

            images.append(avarage_color)
    return images


def matching(input_image, dir):
    """
    matches subsquares of input images with source images according to their average color
    Args:
        input img -- 2D matrix of average color subsquares
        dir -- directory path
    Returns:
        list of matched images in order
    """
    images = get_images(dir)
    average_color_of_images = average_color_of_image(dir)
    matched = []

    for row in input_image:
        for square in row:
            nearest = images[0]
            nearest_color = 257
            for i in range(len(average_color_of_images)):
                pyt_value = pythagoras(square, average_color_of_images[i])
                if nearest_color > pyt_value:
                    nearest = images[i]
                    nearest_color = pyt_value
            matched.append(nearest)
    return matched


def pythagoras(pixel1, pixel2):
    return math.sqrt(pow((pixel2[0] - pixel1[0]), 2) + pow((pixel2[1] - pixel1[1]), 2) + pow((pixel2[2] - pixel1[2]), 2))


def get_images(dir):
    """
    get images from directory and store them in a list
    Args:
        dir -- path
    Returns:
        list of images
    """
    images = []
    for filename in os.listdir(dir):
        if filename.endswith(".jpg"):
            full_src_path = os.path.join(dir, filename)
            source = Image.open(full_src_path)
            # source.resize((300, 300))
            images.append(source)
    return images


def paste(input_img, matched, d, n):
    """
    paste appropriate image into right pixel on source image
    and save photomosaic
    Args:
        input_img -- image
        matched -- image
        d -- input image's height and width
        n -- pixel images' height and width
    """
    i = 0
    for y in range(0, d, n):
        for x in range(0, d, n):
            input_img.paste(matched[i].resize((n, n)), (x, y))
            i += 1
    input_img.show()
    input_img.save("mozaik.jpg")


n = 10
d = 1000
input_image = Image.open("mona.jpg")
img = input_image.resize((d, d))
source_images_directory = "./example_source_images"

# pixelate(out, n).show()
divided = get_divide(img, n)
a = matching(divided, source_images_directory)
paste(img, a, d, n)