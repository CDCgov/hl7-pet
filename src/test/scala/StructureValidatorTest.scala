import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import gov.cdc.hl7.{BatchValidator, StructureValidator, ValidationErrors}
import gov.cdc.hl7.model.Profile
import org.scalatest.flatspec.AnyFlatSpec
import scala.io.Source

/**
  *
  *
  * @Created - 2019-02-08
  * @Author Marcelo Caldas mcq1@cdc.gov
  */
class StructureValidatorTest extends AnyFlatSpec {
    "SingleBatchedMessage" must "pass validation" in {
        val errors = processHappyPathMessage("covid19_elr.hl7")
//        assert(errors.totalErrors == 0)
//        assert(errors.totalWarnings == 0)
    }

    "Structure Validation" must "validate batch" in {
        val errors = processBatchValidation("batchFiles/FileBatchSingleMessage.hl7")
        assert(errors.totalErrors == 27)

    }

    "Structure Validation" must "throw errors on missing MSH" in {
        val errors = processBatchValidation("batchFiles/FileBatchMissingMSH.hl7")
        assert(errors.totalErrors == 18)
    }


    "Structure Validation" must "throw errors on extra FHS fields" in {
        val errors = processBatchValidation("batchFiles/FileBatchExtraFHSFields.hl7")
        assert(errors.totalErrors >0)
    }

    "Structure Validation" must "throw errors on invalid BTS count" in {
        val errors = processBatchValidation("batchFiles/FileBatchInvalidFTSCount.hl7")
        assert(errors.totalErrors >0)
    }

    "Structure Validation" must "throw errors on Repeat BHS" in {
        val errors = processBatchValidation("batchFiles/FileBatchInvalidRepeatBHS3.hl7")
        assert(errors.totalErrors >0)
    }

    "Structure Validation" must "throw errors Multiple BHS" in {
        val errors = processBatchValidation("batchFiles/FileBatchMultipleBHS.hl7")
        assert(errors.totalErrors >0)
    }

    "Structure Validation" must "throw errors Multiple BTS" in {
        val errors = processBatchValidation("batchFiles/FileBatchMultipleBTS.hl7")
        assert(errors.totalErrors >0)
    }
    "Structure Validation" must "throw errors Multiple FHS" in {
        val errors = processBatchValidation("batchFiles/FileBatchMultipleFHS.hl7")
        //assert(errors.totalErrors == 2)
        val found = errors.entries.exists{ it => it.path == "FHS"}
        println(found)
    }




    def processBatchValidation(filename: String): ValidationErrors = {
        val batchProfile= getProfile()
        val allLines = readFile(filename)
        val validator:  StructureValidator = new StructureValidator(allLines, batchProfile, null)
        val errors = validator.validateMessage()
        println(errors)
        errors
    }
    def processHappyPathMessage(filename: String, verbose: Boolean = false): ValidationErrors = {
        val allLines = readFile(filename, verbose)
        val validator:  StructureValidator = new StructureValidator(allLines, null, null)
        val errors = validator.validateMessage()
        println(errors)
        errors
    }

    def readFile(filename: String, verbose: Boolean = false): String = {
        var allLines = ""
        val source = io.Source.fromResource(filename)
        if (verbose) println("Source is null? " + (source == null))
        if (source != null) {
            for (line <- source.getLines) {
                if (verbose) println(s"line: $line")
                allLines += line + "\n"
            }
        }
        allLines
    }

    def getProfile(): Profile = {
        val profileFile = Source.fromResource("DefaultBatchingProfile.json").getLines().mkString("\n")
        val mapper = new ObjectMapper()
        mapper.registerModule(DefaultScalaModule)
        mapper.readValue(profileFile, classOf[Profile])

    }



}
