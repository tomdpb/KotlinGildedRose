package guru.drako.examples.gildedrose

class Shop(val items: List<Item>) {

  private val minQuality = 0
  private val maxQuality = 50

  private fun updateStandardItem(item: Item){
    //quality decreases by two if the sell in date has passed
    if (item.sellIn == 0){
      item.quality = (item.quality - 2).coerceIn(minQuality, maxQuality)

    } else {
      item.quality = (item.quality - 1).coerceIn(minQuality, maxQuality)
    }
  }
  private fun updateAgedBrieItem(item: Item){
    //quality increases as it ages
    item.quality = (item.quality + 1).coerceIn(minQuality, maxQuality)
  }
  private fun updateBackstageItem(item: Item){
    //quality changes based on how many days are left to the concert
    when {
      item.sellIn <= 0  -> item.quality = 0
      item.sellIn <= 5  -> item.quality = (item.quality + 3).coerceIn(minQuality, maxQuality)
      item.sellIn <= 10 -> item.quality = (item.quality + 2).coerceIn(minQuality, maxQuality)
      else              -> item.quality = (item.quality + 1).coerceIn(minQuality, maxQuality)
    }
  }
  private fun updateConjuredItem(item: Item){
    //quality simply degrades twice as fast as a standard item
    updateStandardItem(item)
    updateStandardItem(item)
  }

  fun updateQuality(){
    loop@ for (item in items){
      when {
        item.name.contains("Sulfuras", ignoreCase = true) -> continue@loop //legendary item. Do nothing
        item.name.contains("Aged Brie", ignoreCase = true) -> updateAgedBrieItem(item)
        item.name.contains("Backstage passes", ignoreCase = true) -> updateBackstageItem(item)
        item.name.contains("Conjured", ignoreCase = true) -> updateConjuredItem(item)
        else -> updateStandardItem(item)
      }
      item.sellIn--
    }
  }
}
