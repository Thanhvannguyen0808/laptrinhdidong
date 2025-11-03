package com.example.homeworkt4_bai1.model

class LibraryManager {

    private val allBooks = mutableListOf(
        Book(1, "Sách 01"),
        Book(2, "Sách 02"),
        Book(3, "Sách 03"),
        Book(4, "Sách 04"),
        Book(5, "Sách 05")
    )

    private val students = mutableListOf(
        Student(1, "Nguyen Van A", mutableListOf(allBooks[0], allBooks[1])),
        Student(2, "Nguyen Thi B", mutableListOf(allBooks[0])),
        Student(3, "Nguyen Van C", mutableListOf())
    )

    fun getStudents(): List<Student> = students

    fun getAllBooks(): List<Book> = allBooks

    fun getBorrowedBooks(student: Student): List<Book> = student.borrowedBooks

    fun addBookToStudent(student: Student, book: Book) {
        if (!student.borrowedBooks.contains(book)) {
            student.borrowedBooks.add(book)
        }
    }

    fun toggleBookBorrowed(student: Student, book: Book) {
        if (student.borrowedBooks.contains(book)) {
            student.borrowedBooks.remove(book)
        } else {
            student.borrowedBooks.add(book)
        }
    }

    fun getBorrowerOfBook(book: Book): String? {
        val borrower = students.find { it.borrowedBooks.contains(book) }
        return borrower?.name
    }
}
