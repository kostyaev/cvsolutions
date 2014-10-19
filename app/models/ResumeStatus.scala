package models

object ResumeStatus { self =>
  final val NOT_PAID = "Не оплачено"
  final val PAID = "Оплачено"
  final val IN_PROCESS = "В обработке"
  final val COMPLETED = "Готово"

  def statuses[A: scala.reflect.runtime.universe.TypeTag](a: A) = {
    import scala.reflect.runtime.universe._

    def members(s: Symbol): Map[String, String] =
      s.typeSignature.declarations.collect {
        case m: ModuleSymbol => members(m)
        case m: MethodSymbol if m.isAccessor => m.returnType match {
          case ConstantType(Constant(s: String)) => Map(m.name.decoded -> s)
          case _ => Map.empty[String, String]
        }
      }.foldLeft(Map.empty[String, String])(_ ++ _)

    members(typeOf[A].termSymbol)
  }

  def getStatuses = statuses(self)
}
