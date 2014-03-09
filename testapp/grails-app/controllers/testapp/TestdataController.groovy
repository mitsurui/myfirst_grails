package testapp



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TestdataController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Testdata.list(params), model:[testdataInstanceCount: Testdata.count()]
    }

    def show(Testdata testdataInstance) {
        respond testdataInstance
    }

    def create() {
        respond new Testdata(params)
    }

    @Transactional
    def save(Testdata testdataInstance) {
        if (testdataInstance == null) {
            notFound()
            return
        }

        if (testdataInstance.hasErrors()) {
            respond testdataInstance.errors, view:'create'
            return
        }

        testdataInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'testdataInstance.label', default: 'Testdata'), testdataInstance.id])
                redirect testdataInstance
            }
            '*' { respond testdataInstance, [status: CREATED] }
        }
    }

    def edit(Testdata testdataInstance) {
        respond testdataInstance
    }

    @Transactional
    def update(Testdata testdataInstance) {
        if (testdataInstance == null) {
            notFound()
            return
        }

        if (testdataInstance.hasErrors()) {
            respond testdataInstance.errors, view:'edit'
            return
        }

        testdataInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Testdata.label', default: 'Testdata'), testdataInstance.id])
                redirect testdataInstance
            }
            '*'{ respond testdataInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Testdata testdataInstance) {

        if (testdataInstance == null) {
            notFound()
            return
        }

        testdataInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Testdata.label', default: 'Testdata'), testdataInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'testdataInstance.label', default: 'Testdata'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
