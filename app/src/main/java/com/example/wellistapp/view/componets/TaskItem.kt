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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wellistapp.R
import com.example.wellistapp.data.Priority
import com.example.wellistapp.data.Task
import java.util.Date

@Composable
fun TaskItem (
    task: Task
){
    val priorityLevel: String = when(task.priority) {
        Priority.HIGH -> Priority.HIGH.toString()
        Priority.MEDIUM ->  Priority.MEDIUM.toString()
        Priority.LOW -> Priority.LOW.toString()

    }
    var checked by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.fillMaxWidth()
            .height(160.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified)
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
                color = Color.Red
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

                )
            )

        }
        Row (//Linha dos icones
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()

        )
        {
            IconButton(
                onClick = { //Implementar BD (edição)

                }
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
                onClick = { //Implementar BD (delete)

                }
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
                checked = false,
                onCheckedChange = {checked = true},
                modifier = Modifier.scale(0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview () {
    TaskItem(Task(
        1,
        "Title",
        "Uma descrição bem grande, de novo, para testar suficientemente o espaçamento entre as linhas e o tamanho da column",
        Priority.HIGH,
        Date(),
        null))
}