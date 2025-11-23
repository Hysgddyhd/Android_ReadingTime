package com.wuyuntian.a197547_readingtime.ui

import androidx.lifecycle.ViewModel
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.model.PlanUiState
import com.wuyuntian.a197547_readingtime.room.Plan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class PlanViewModel : ViewModel() {
    val _uiState: MutableStateFlow<PlanUiState> = MutableStateFlow(PlanUiState())
    val uiState : StateFlow<PlanUiState> = _uiState.asStateFlow()


//     init {
//        resetPlan()
//    }
    fun resetPlan() {
        _uiState.update { currentState->
            currentState.copy(
                book = Book("", listOf(""), pageCount = 0, imageResourceID = R.drawable.no_photo),
                currentPage=0,
                period  = 30,
                priority = 1,
                start_day = LocalDate.now()
            )

        }

    }

//select book in second screen

    fun updateSelectedBook(select_book : Book ){
        _uiState.update { currentState->
            currentState.copy(
                book = select_book
            )
        }

    }
    //update booklist
//    fun updateBooklist(new_booklist:List<Book>){
//        val i:Int = new_booklist.size;
//        _uiState.update{ currentState ->
//            currentState.copy(
//                booklist =new_booklist,
//                bookCount = uiState.value.bookCount.and(i)
//            )
//        }
//    }

    fun updatePeriod(input:String){
        val day_input=input.plus("")
        if(!input.equals("")){
            val days = day_input.toInt()
            _uiState.update { currentState->
                currentState.copy(
                    period = days
                )
            }
        }else{

        }

    }
    fun updatePriority(input: String){
        val priority_input=input
        if(!input.equals("")){
            val priority_1 = priority_input.toInt()
            _uiState.update { currentState->
                currentState.copy(
                    priority = priority_1
                )
            }
        }
    }



    //update pages per day
    fun loadPlan(
        savedPlan: Plan
    ){
        updateSelectedBook(savedPlan.book)
        updatePeriod(savedPlan.period.toString())
        updatePriority(savedPlan.priority.toString())
        _uiState.update{ currentState ->
            currentState.copy(
                currentPage = savedPlan.reading_progress,
                start_day = savedPlan.start_day
            )
        }

         }


    //update progress
    fun updateProgress(
        page_read:Int
    ){
        _uiState.update { currentState->
            currentState.copy(
            )

        }
    }

    fun toPlan(): Plan{
        return Plan(
            book = uiState.value.book,
            priority = uiState.value.priority,
            reading_progress = uiState.value.currentPage,
            period = uiState.value.period,
            start_day = uiState.value.start_day
        )
    }


}