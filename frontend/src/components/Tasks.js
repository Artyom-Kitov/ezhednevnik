import React from "react"
import Task from "./Task"

class Tasks extends React.Component {
  render() {
    console.log(this.props.tasks)
    if (this.props.tasks.length > 0) {
      return (
        <div>
          {this.props.tasks.map((el) => (
            <Task onEdit={this.props.onEdit} onDelete={this.props.onDelete} key={el.id} task={el}/>
          ))}
        </div>
      )
    }
    return (
      <div className="notask">
        <h3>No tasks</h3>
      </div> 
    )

  }

}

export default Tasks