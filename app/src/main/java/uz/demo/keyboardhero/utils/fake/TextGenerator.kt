package uz.demo.keyboardhero.utils.fake

object TextGenerator {

    private val LOREM_IPSUM_SOURCE = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam id ante in justo congue convallis. Integer lorem felis, ultricies vel orci eget, fringilla scelerisque lorem. Ut ultricies vulputate urna quis mollis. Suspendisse potenti. Pellentesque nec diam ac mauris varius elementum a eu quam. Cras tristique tempor nisi sed interdum. Donec tincidunt laoreet nisl, sit amet lacinia tortor dignissim sed. Suspendisse id felis quis est finibus fringilla. Duis consequat eros ipsum, quis cursus nunc condimentum vitae. Mauris aliquam nibh a tortor cursus, tristique mattis felis cursus. Praesent est lacus, vulputate eget tortor et, elementum lobortis nisi. Duis dapibus ultrices malesuada.

        Morbi eu purus non nisi maximus feugiat vel et erat. Duis sit amet ultricies ex. Sed volutpat nunc turpis, ut varius libero interdum vitae. Nam dictum, est nec placerat dapibus, magna ligula porttitor neque, in vulputate lorem tortor a turpis. Maecenas maximus eget purus sit amet eleifend. Sed volutpat rhoncus neque. Aenean placerat sodales nisi eget pharetra. Donec sem mauris, sodales eget mauris non, sollicitudin eleifend elit. Sed et fringilla purus. Donec vulputate diam et purus interdum, id tempus orci placerat. Quisque vehicula, ex sed condimentum ornare, sapien magna elementum dolor, quis aliquet dolor dui eu magna.
        
        Cras vestibulum, nisi vel ornare feugiat, massa tortor rutrum nisl, et porttitor nunc est vitae risus. In lobortis semper quam, quis pharetra quam tempus nec. Sed non mattis tellus. Phasellus lacus dolor, sodales ultricies faucibus dictum, malesuada in nibh. Donec et dui vel ex sodales aliquam et fringilla quam. Ut sodales magna quam, ut elementum velit lacinia at. Fusce eget finibus augue. Aliquam id suscipit tortor. Fusce ullamcorper accumsan nibh ut volutpat. Pellentesque turpis tellus, facilisis in odio ultricies, auctor finibus nisl. Maecenas semper maximus felis. Suspendisse sodales dapibus velit, vel blandit mi bibendum eu. In eu enim ultricies, porttitor est a, auctor elit. Morbi dapibus urna quis nunc iaculis, sed porttitor tellus consectetur. Donec semper vestibulum ante sed feugiat.
        
        In posuere laoreet tellus ut porta. Etiam sodales, elit in elementum mattis, quam tellus venenatis est, in laoreet odio est ac lacus. Aenean sodales a lectus id egestas. Curabitur sit amet felis neque. Fusce accumsan risus massa. Quisque aliquet justo ut arcu fringilla pellentesque. Praesent ante dui, viverra quis lectus vel, laoreet commodo nisi.
        
        Vivamus vel fermentum leo, vitae blandit sapien. Ut a erat in mauris molestie lacinia. Phasellus laoreet tortor a ante maximus, vitae hendrerit enim suscipit. Ut pretium leo et felis faucibus egestas non a nisi. Aliquam porttitor leo quis massa porttitor, ac consequat lectus venenatis. Suspendisse potenti. Donec tincidunt efficitur ex, vitae rutrum ipsum molestie rhoncus. Duis eros arcu, tristique a tincidunt quis, venenatis vitae turpis. Quisque mollis pellentesque diam et malesuada. Quisque pharetra mattis ipsum in molestie. Duis sed risus pellentesque, consequat justo ac, viverra augue.
        """.trim().split(" ")

    fun generateText(words: Int = 200): String {
        var wordsUsed = 0
        val loremIpsumMaxSize = LOREM_IPSUM_SOURCE.size
        return generateSequence {
            LOREM_IPSUM_SOURCE[wordsUsed++ % loremIpsumMaxSize]
        }.take(words).joinToString(" ")
    }
}
