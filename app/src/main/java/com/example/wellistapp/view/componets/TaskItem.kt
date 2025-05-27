package com.example.wellistapp.view.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wellistapp.R
import com.example.wellistapp.data.Priority
import com.example.wellistapp.data.Task


@Composable
fun TaskItem (
    task: Task,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onCheckedChange:(Boolean) -> Unit
){

    val priorityLevel: String = task.priority.toString()

    var color = when(priorityLevel) {
        Priority.HIGH.toString() -> Color.Red
        Priority.MEDIUM.toString() -> Color.Yellow
        else -> Color.Green
    }

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row ( //Linha Titulo/priority
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text( //TITULO
                text = task.title,
                fontSize = 18.sp,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Left,
                maxLines = 1,
                color = Color.DarkGray,

                )
            Text(//Priority
                text = priorityLevel,
                fontSize = 12.sp,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.End,
                color = color
            )
        }
        Column(//Coluna
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp,top = 1.dp, bottom = 10.dp)


        ){
            Text(
                text = task.description,
                color = Color.Gray,
                fontSize = 10.sp,
                fontFamily = FontFamily.Default,
                maxLines = Int.MAX_VALUE,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Paragraph,
                    hyphens = Hyphens.Auto

                ),
                modifier = Modifier.padding(end = 10.dp)
            )

        }
        Row (//Linha dos icones
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()

        )
        {
            IconButton(
                onClick = {onEdit(task.id)}
            ) {
                Icon( //Editar tarefa
                    painter = painterResource(R.drawable.baseline_edit_24),
                    contentDescription = "",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                )
            }
            IconButton(
                onClick = {onDelete(task.id)}
            ) {
                Icon( //Excluir tarefa
                    painter = painterResource(R.drawable.outline_delete_24),
                    contentDescription = "",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                )
            }
            Checkbox( //Concluir tarefa
                checked = task.isDone,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.scale(0.8f)
            )
        }
    }
}
