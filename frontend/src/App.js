import React from "react"
import Header from "./components/Header"
import Tasks from "./components/Tasks"
import AddTask from "./components/AddTask"

class App extends React.Component {
   
  constructor(props) {
    super(props)
    this.state = {
      tasks: [
        // {
        //   id: 1,
        //   name: "Полить цветы",
        //   description: "А то завянут",
        //   priority: "critical",
        //   stat: "to do",
        //   created_at: "2024-10-24T12:00:00Z",
        //   deadline: new Date("2024-10-25")
        // },
    
        // {
        //   id: 2,
        //   name: "Сделать окружающий мир",
        //   description: "Задание 235: указать тип почвы",
        //   priority: "low",
        //   stat: "in progress",
        //   created_at: "2024-10-23T12:10:00Z",
        //   deadline: new Date("2024-10-29")
        // },
      ]

    }
    this.addTask = this.addTask.bind(this)
    this.editTask = this.editTask.bind(this)
    this.deleteTask = this.deleteTask.bind(this)
  }


  render() {
    return (
      <div>
        <Header title="TaskGuru" />
        <main>
          <Tasks tasks={this.state.tasks} onEdit={this.editTask} onDelete={this.deleteTask} />
        </main>
        <aside>
          <AddTask onAdd={this.addTask} />
        </aside>
      </div>)
  }

  addTask(task) {
    const id = this.state.tasks.length + 1
    this.setState({tasks: [...this.state.tasks, {id, ...task}]})
  }

  editTask(task) {
    let allTasks = this.state.tasks
    allTasks[task.id - 1] = task
    this.setState({tasks: []}, () => {
      this.setState({tasks: [...allTasks]})
    })
  }

  deleteTask(id) {
    this.setState({
      tasks: this.state.tasks.filter((el) => el.id !== id)
    })
  }

}

export default App