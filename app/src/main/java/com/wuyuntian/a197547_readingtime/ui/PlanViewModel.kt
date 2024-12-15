package com.wuyuntian.a197547_readingtime.ui

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import com.wuyuntian.a197547_readingtime.Book
import com.wuyuntian.a197547_readingtime.Plan
import com.wuyuntian.a197547_readingtime.R
import com.wuyuntian.a197547_readingtime.model.PlanUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlanViewModel : ViewModel() {
    var select_book:Book = Book("", listOf(""), pages = 0, imageResourceID = R.drawable.no_photo)
    private val _uiState = MutableStateFlow(PlanUiState())
    val uiState: StateFlow<PlanUiState> = _uiState.asStateFlow()
//select book in second screen

    fun updateSelectedBook(book1 : Book ){
        _uiState.update { currentState->
            currentState.copy(
                book = book1,
                plan = Plan(book1,0,0)


            )
        }
    }
    //update booklist
    fun updateBooklist(new_booklist:List<Book>){
        val i:Int = new_booklist.size;
        _uiState.update{ currentState ->
            currentState.copy(
                booklist =new_booklist,
                bookCount = uiState.value.bookCount.and(i)
            )
        }
    }

    fun updatePlan(input:String){
         if(input.equals("")) {
             Log.d("error", "can't be null")
             _uiState.update { currentState->
            currentState.copy(
                plan = Plan(uiState.value.book,0,0)
            )

            }

            } else{
                var days = input.toInt()
        _uiState.update { currentState->
            currentState.copy(
                plan = Plan(uiState.value.book,days,0)
            )

            }
        }

    }

    //update pages per day
    fun updatePlan(
        new_pages:Int,
        new_period:Int,
        new_current_day:Int
    ){
        val new_page_per_day:Double=new_pages.div(new_period.toDouble());
         _uiState.update { currentState->
             currentState.copy(
                 pages=new_pages,
                 period = new_period,
                 current_day = new_current_day,
                 pagePerDay = new_page_per_day,
             )

         }

    }

    //update progress
    fun updateProgress(
        page_read:Int
    ){
         _uiState.update { currentState->
             currentState.copy(
                    pageCount = uiState.value.pageCount.and(page_read),
                 current_day = uiState.value.current_day.inc()
             )

         }
    }
}