package guru.drako.examples.gildedrose

import kotlin.test.Test
import org.junit.jupiter.api.Assertions.*

class ShopTest {

  @Test
  fun testQualityUpdate(){
  val shop = Shop(items = listOf(
    Item(name = "+5 Dexterity Vest", sellIn = 10, quality = 20),
    Item(name = "Aged Brie", sellIn = 2, quality = 0),
    Item(name = "Elixir of the Mongoose", sellIn = 5, quality = 7),
    Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 0, quality = 80),
    Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 20),
    Item(name = "Conjured Mana Cake", sellIn = 3, quality = 6)))

    shop.updateQuality()
    assertEquals(19, shop.items[0].quality)
    assertEquals(1, shop.items[1].quality)
    assertEquals(6, shop.items[2].quality)
    assertEquals(80, shop.items[3].quality) //legendary item shouldnt change
    assertEquals(21, shop.items[4].quality)
    assertEquals(4, shop.items[5].quality)
  }

  @Test
  fun testBackstagePasses(){
    val passes = Shop(items = listOf(
      Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 0, quality = 20),
      Item(name = "Backstage passes to a Queen concert", sellIn = 2, quality = 20),
      Item(name = "Backstage passes to a Lofi Hip-Hop girl concert", sellIn = 10, quality = 20),
      Item(name = "Backstage passes to a Hillary Hahn concert", sellIn = 20, quality = 20)
    ))
    //quality changes based on how many days are left to the concert
    passes.updateQuality()
    assertEquals(0, passes.items[0].quality)
    assertEquals(23, passes.items[1].quality)
    assertEquals(22, passes.items[2].quality)
    assertEquals(21, passes.items[3].quality)
  }

  @Test
  fun testRanges(){
    val wares = Shop(items = listOf(
      Item(name = "Aged Brie", sellIn = 10, quality = 50),
      Item(name = "Conjured TI-89 Calculator", sellIn = 3, quality = 1),
      Item(name = "Huge Conjured Chocolate Bar", sellIn = 0, quality = 0),
      Item(name = "A Single Biscuit", sellIn = 0, quality = 0)))
    //qualities should not exceed 50 nor fall behind 0
    wares.updateQuality()
    assertEquals(50, wares.items[0].quality)
    assertEquals(0, wares.items[1].quality)
    assertEquals(0, wares.items[2].quality)
    assertEquals(0, wares.items[3].quality)
  }
}