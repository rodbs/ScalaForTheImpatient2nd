import java.awt.datatransfer.{DataFlavor, SystemFlavorMap}

def randArray(n: Int) = Array.fill(n)(util.Random.nextInt(100))

val a = randArray(5)


val arr81 = Array(1,3,-4,4,7,-3,6,-4,7)
arr81.indices
val indToDel = arr81.indices.filter(arr81(_)<0).drop(1)
for (i <- 0 until arr81.length if !indToDel.contains(i)) yield arr81(i)




val id = "America/"
java.util.TimeZone.getAvailableIDs.filter(_.startsWith(id))
java.util.TimeZone.getAvailableIDs.filter(_.startsWith(id)).map(_.stripPrefix(id)).sorted


/**
  * 11. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with
the call
val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor
and get the return value as a Scala buffer. (Why this obscure class? It’s hard
to find uses of java.util.List in the standard Java library.)
  */
}
val flavors = java.awt.datatransfer.SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]

val buff = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
