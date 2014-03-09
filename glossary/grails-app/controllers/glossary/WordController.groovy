package glossary



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WordController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Word.list(params), model:[wordInstanceCount: Word.count()]
    }

    def show(Word wordInstance) {
        respond wordInstance
    }

    def create() {
        respond new Word(params)
    }

    @Transactional
    def save(Word wordInstance) {
        if (wordInstance == null) {
            notFound()
            return
        }

        if (wordInstance.hasErrors()) {
            respond wordInstance.errors, view:'create'
            return
        }

        wordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wordInstance.label', default: 'Word'), wordInstance.id])
                redirect wordInstance
            }
            '*' { respond wordInstance, [status: CREATED] }
        }
    }

    def edit(Word wordInstance) {
        respond wordInstance
    }

    @Transactional
    def update(Word wordInstance) {
        if (wordInstance == null) {
            notFound()
            return
        }

        if (wordInstance.hasErrors()) {
            respond wordInstance.errors, view:'edit'
            return
        }

        wordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Word.label', default: 'Word'), wordInstance.id])
                redirect wordInstance
            }
            '*'{ respond wordInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Word wordInstance) {

        if (wordInstance == null) {
            notFound()
            return
        }

        wordInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Word.label', default: 'Word'), wordInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wordInstance.label', default: 'Word'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
