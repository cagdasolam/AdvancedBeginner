from PIL import Image
from colorama import Style, Fore

im = Image.open("mona.jpg")
out = im.resize((281, 281))


# filling pixel_ matrix
def fill_pixel_matrix(image):
    image.thumbnail((500, 500))
    pixels = list(image.getdata())
    pixel_matrix = []
    for i in range(0, len(list(pixels)), image.width):
        raw = []
        for j in range(image.width):
            raw.append(pixels[i + j])
        pixel_matrix.append(raw)
    return pixel_matrix


# convert pixel values to brigtness value
def fill_brightness_matrix(matrix):
    brightness_matrix = []
    for i in range(len(matrix)):
        matrix_raw = []
        for j in range(len(matrix[i])):
            average_pix = (matrix[i][j][0] + matrix[i]
                           [j][1] + matrix[i][j][2]) / 3
            matrix_raw.append(average_pix)
        brightness_matrix.append(matrix_raw)
    return brightness_matrix


def normalize_matrix(intensity_matrix):
    normalized_matrix = []
    max_pixel = max(map(max, intensity_matrix))
    min_pixel = min(map(min, intensity_matrix))
    for row in intensity_matrix:
        rescaled_row = []
        for p in row:
            r = 255 * (p - min_pixel) / float(max_pixel - min_pixel)
            rescaled_row.append(r)
        normalized_matrix.append(rescaled_row)
    return normalized_matrix


def convert_to_ascii(intensity_matrix, ascii_chars):
    ascii_matrix = []
    for row in intensity_matrix:
        ascii_row = []
        for p in row:
            ascii_row.append(ascii_chars[int(p/255 * len(ascii_chars)) - 1])
        ascii_matrix.append(ascii_row)
    return ascii_matrix


def print_ascii_matrix(ascii_matrix, text_color):
    for row in ascii_matrix:
        line = [p+p+p for p in row]
        print(text_color + "".join(line))
    print(Style.RESET_ALL)


ASCII_CHARS = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
matrix = fill_pixel_matrix(out)
brightness = fill_brightness_matrix(matrix)
brightness = normalize_matrix(brightness)
ascii_matrix = convert_to_ascii(brightness, ASCII_CHARS)
print_ascii_matrix(ascii_matrix, Fore.GREEN)
