import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.latin._
import scala.io.Source
import java.io.PrintWriter



// Normally, just "." in an MID project
val repoRoot = "."


// Standard way to validate:
// 1. define readers your project uses
val readerMap : Map[String, Vector[MidMarkupReader]] = Map(
  "MidProseABDiplomatic" ->   Vector(MidProseABDiplomatic)
)

// 2. define orthographies your project uses
/*
val orthoMap : Map[String, MidOrthography] = Map(
  "Latin23" -> Latin23
)
*/

// 3. Build a validator. This requires ortho map as well as a CITE library.
val repo = EditorsRepo(repoRoot, readerMap)

def validate = {
  val lib =    repo.library
  val dseValidator = DseValidator(lib)
  val validators = Vector(dseValidator)
  val rslts = LibraryValidator.validate(lib, validators)

  val formatted =  TestResultGroup("Valdation results", rslts)
  val output = "validation/report.md"
  new PrintWriter(output){write(formatted.markdown);close;}
  println("Results are in " + output)
}



println("\n\nTo validate your work, run:\n")
println("\tvalidate\n\n")
