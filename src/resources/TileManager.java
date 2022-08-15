package resources;


import gui.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The type Tile manager.
 */
//creates map and holds information about every tile
public class TileManager {
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 1024;
	private static final double FEATURE_SIZE = 24;

	/**
	 * The Tiles.
	 */
	public Tile[] tiles = new Tile[35];
	/**
	 * The GamePanel.
	 */
	GamePanel gp;
	/**
	 * The Map.
	 */
	public int[][] map;

	/**
	 * Instantiates a new Tile manager.
	 *
	 * @param gp the GamePanel
	 */
	public TileManager(GamePanel gp) {
		
		
		tiles[0] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/grass00.png")), false, true);
		tiles[1 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/wall.png")), true, false);
		tiles[2 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water00.png")), true, false);
		tiles[3 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/grass01.png")), false, true);
		tiles[4 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/tree.png")), true, false);
		tiles[5 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road00.png")), false, false);
		tiles[6 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road01.png")), false, false);
		tiles[7 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road02.png")), false, false);
		tiles[8 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road03.png")), false, false);
		tiles[9 ] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road04.png")), false, false);
		tiles[10] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road05.png")), false, false);
		tiles[11] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road06.png")), false, false);
		tiles[12] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road07.png")), false, false);
		tiles[13] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road08.png")), false, false);
		tiles[14] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road09.png")), false, false);
		tiles[15] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road10.png")), false, false);
		tiles[16] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road11.png")), false, false);
		tiles[17] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/road12.png")), false, false);
		tiles[18] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/table01.png")), true, false);
		tiles[19] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/hut.png")), true, false);
		tiles[20] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/floor01.png")), false, false);
		tiles[21] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/earth.png")), false, false);
		tiles[22] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water01.png")), true, false);
		tiles[23] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water02.png")), true, false);
		tiles[24] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water03.png")), true, false);
		tiles[25] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water04.png")), true, false);
		tiles[26] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water05.png")), true, false);
		tiles[27] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water06.png")), true, false);
		tiles[28] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water07.png")), true, false);
		tiles[29] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water08.png")), true, false);
		tiles[30] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water09.png")), true, false);
		tiles[31] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water10.png")), true, false);
		tiles[32] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water11.png")), true, false);
		tiles[33] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water12.png")), true, false);
		tiles[34] = new Tile(new Image(getClass().getResourceAsStream("/images/tiles/water13.png")), true, false);

		this.gp = gp;
	}

	/**
	 * Create map.
	 *
	 * @param filename the filename
	 */
	public void createMap(String filename) {
		map = new int[50][50];
		try (Scanner input = new Scanner(Files.newInputStream(Paths.get("src/files/" + filename + ".txt")))) {

			int rij = 0;
			while (input.hasNextLine()) {
				String[] arrayString = input.nextLine().split(" ");
				for (int i = 0; i < arrayString.length; i++) {
					map[i][rij] = Integer.parseInt(arrayString[i]);
				}
				rij++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void generateMapFromImage(Image image) {
		int w = (int) Math.floor(image.getWidth());
		int h = (int) Math.floor(image.getHeight());
		System.err.print("Width " + w + " Height " + h);
		map = new int[w][h];
		for (int row = 0; row < h; row++) {
			for (int column = 0; column < w; column++) {
				PixelReader pixelR = image.getPixelReader();
				int pixel = pixelR.getArgb(column, row);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 102 && green == 163 && blue == 93) {
					map[column][row] = 0;
				}
				if (red == 64 && green == 145 && blue == 202) {
					map[column][row] = 23;
				}
				if (red == 49 && green == 91 && blue == 43) {
					map[column][row] = 4;
				}

				if (red == 195 && green == 153 && blue == 78) {
					map[column][row] = 5;
				}

				if (red == 255 && green == 127 && blue == 39) {
					map[column][row] = 20;
				}
				if (red == 195 && blue == 195 && green == 195) {
					map[column][row] = 1;
				}
				if (red == 255 && green == 201 && blue == 14) {
					map[column][row] = 18;
				}


			}
		}
	}

	public void generateRandomMap(){
//		BufferedImage img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

		OpenSimplexNoise noise = new OpenSimplexNoise();
		map = new int[WIDTH][HEIGHT];
//		int color;

		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE);


				if (value < 0) {
					map[x][y] = 22;
//					color = 256*256*5+256*17+245;
//					img.setRGB(x,y,color);

				}
				if (value > -0.5 && value < -0.25) {
					map[x][y] = 4;
//					color = 256*256*33+256*89+4;
//					img.setRGB(x,y,color);
				}

				if (value > -0.25 && value < 0){
					map[x][y] = 0;
				}

				if (value > 0 && value <0.3) {
					map[x][y] = 5;
//					color = 256*256*128+256*77+15;
//					img.setRGB(x,y,color);
				}

				if (value > 0.3) {
					map[x][y] = 3;
//					color = 256*256*5+256*245+101;
//					img.setRGB(x,y,color);
				}


			}

		}

		/*try {
			ImageIO.write(img,"png", new File("noise2.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
	}

	/**
	 * Draw map.
	 *
	 * @param gc the GraphicsContext
	 */
	public void drawMap(GraphicsContext gc) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.MaxWorldRow) {

			int tileNum = map[worldCol][worldRow];

			int worldX = worldCol * GamePanel.TILESIZE;
			int worldY = worldRow * GamePanel.TILESIZE;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + GamePanel.TILESIZE > gp.player.worldX - gp.player.screenX
					&& worldX - GamePanel.TILESIZE < gp.player.worldX + gp.player.screenX
					&& worldY + GamePanel.TILESIZE > gp.player.worldY - gp.player.screenY
					&& worldY - GamePanel.TILESIZE < gp.player.worldY + gp.player.screenY) {
				
				gc.drawImage(tiles[tileNum].getImage(), screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE);
			}
			
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}
}
