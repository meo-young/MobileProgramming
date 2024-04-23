import java.time.LocalDate
import java.util.*

fun main() {
    val num = decimalDigitValue('7')
    println(num*2)

//    testString()

//    testOperator()

//    scannerExample()

//    ifExample()

//    whenExample1()

//    whenExample2()

    forExample1()

//    forExample2()

}

fun forExample2() {
    val numbers = arrayOf(1,2,3,4,5)
    val item = Array<String>(5){i->(i*i).toString()}
    for(num in numbers){
        print("$num\t")
    }
    println()

    for(index in numbers.indices){
        println("number at $index is ${numbers[index]}")
    }
    for(fu in item)
        println(fu)
    item[1] = "3"
    for(fu in item)
        println(fu)
}

fun forExample1() {

    fun isEven(num:Int):String = when(num%2){
        0 -> "짝"
        else -> "홀"
    }

    println(isEven(100))

    val start = LocalDate.now()
    val end = start..start.plusDays(15)
    println(start.plusWeeks(1) in end)
    println(end)

}

fun whenExample2(){
    val x = 9
    when(x){
        1 -> println("x==1")
        2,3 -> println("x==2 or x==3")
        in 4..7 -> println("4부터 7사이")
        !in 8..10 -> println("8부터 10사이가 아님")
        else ->{
            print("x는 8에서 10사이의 숫자임")
        }
    }
}

fun whenExample1(){
    val a = 12
    val b = 5
    println("Enter operator either +, -, * or /")
    val op = readln()
    val result = when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> "$op operator is invalid operator."
    }
    println(result)
}

fun ifExample(){
    val a = -9
    val b = -11
    val max = if (a > b) {
        println("$a is larger than $b.")
        println("max variable holds value of a.")
        a
    } else {
        println("$b is larger than $a.")
        println("max variable holds value of b.")
        b
    }
    println("max value : $max")
}

fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.code - '0'.code
}

fun testString(){
    val s1 = "홍길동"
    val s2 = "5".toInt()
    val s3 = "123.5".toDouble()
    println("이름: $s1 \n번호 : ${s2}번 \n응모가 : ${s3-10}원")
}

fun testOperator(){
    val start = LocalDate.now()
    println(start)
    val end = start..start.plusDays(15)
    println(start.plusWeeks(1) in end)
    println(end)
}

fun scannerExample(){
    val reader = Scanner(System.`in`)
    print("Enter a number: ")
    var integer:Int = reader.nextInt()
    println("You entered: $integer")
}



