import play.api.db.DB
import play.api.mvc.{Results, SimpleResult, RequestHeader}
import play.api.{Application, GlobalSettings}
import anorm._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object Global extends GlobalSettings {

  override def onStart(app: Application) {

    import play.api.Play.current

    DB.withConnection { implicit connection =>
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Честер Беннингтон', 'chester@lp.com')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Рик Мартин', 'ricky@mail.ru')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Alvin Alexander', 'mail@alvinalexander.com')").execute()
      SQL("INSERT INTO contacts(name, emailAddress) VALUES('Джек Воробей', 'yahoo@yahoo.com')").execute()
    }
  }

  override def onHandlerNotFound(request: RequestHeader) = {
    Future(Results.NotFound(views.html.notFound()))
  }
}
