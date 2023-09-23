
$(document).ready(function () {
    var user = JSON.parse(localStorage.getItem("user"));
    var userId = user.userId;
    var token = user.uuid;

    // Function to display tasks of the user
    function display(data) {
        var taskListBody = $("#taskListBody");
        taskListBody.empty();

        $.each(data, function (index, task) {
            // Create table row and cells
            var row = $("<tr>");
            var col1 = $("<td>").text(task.id);
            var col2 = $("<td>").text(task.title);
            var col3 = $("<td>").text(task.description);
            var col4 = $("<td>").text(task.dueDate);
            var st = $("<td>").text(task.dueDate);
            var actionCol = $("<tr>");
            task.completed ? st.text("Procession") : st.text("Completed");
            var markBtn = $("<button>")
                .addClass("btn btn-info btn-sm ml-2 w-75")
                .text("Change Status");

           
            var col6 = $("<td>").text(task.userId);
            var col7 = $("<td>").text(task.name);
            var statusbtn = $("<td>").append( markBtn);
            actionCol.append(statusbtn);

            var btn = $("<button>")
                .html(`Delete`)
                .addClass("btn btn-danger");

            var inp = $("<input>")
                .attr("placeholder", "User Id")
                .attr("id", "userId");
            var assign = $("<button>")
                .text("Assign")
                .addClass("btn btn-success btn-sm ml-1");

            var editBtn = $("<button>")
                .addClass("btn btn-success")
                .html(`Update`);


            var delBtn = $("<td>").append(btn);
            actionCol.append(delBtn);
            var col9 = $("<td>").append(inp, assign);
            var editCol = $("<td>").append(editBtn);
            actionCol.append(editCol);

            row.append(col1, col2, col3, col4, col6, col7, col9,st,actionCol);

            btn.on("click", function () {
                deleteTask(task);
            });
            markBtn.on("click", function () {
                markTask(task);
            });
            assign.on("click", function () {
                assignTask(task);
            });
            editBtn.on("click", function () {
                editTask(task);
            });

            taskListBody.append(row);
        });
    }

    // Function to delete a task
    function deleteTask(task) {
        var taskId = task.id;
        var api_link = `http://localhost:8080/api/tasks/${token}/${taskId}`;
        $.ajax({
            url: api_link,
            type: "DELETE",
            contentType: "application/json",
            success: function (data) {
                if (data.message != null) {
                    alert(data.message);
                } else {
                    location.reload();
                }
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    }

    // Function to mark a task as complete or pending
    function markTask(task) {
        var taskId = task.id;
        var api_link = `http://localhost:8080/api/tasks/complete/${token}/${taskId}`;
        $.ajax({
            url: api_link,
            type: "PUT",
            contentType: "application/json",
            success: function (data) {
                if (data.message != null) {
                    alert(data.message);
                } else {
                    location.reload();
                }
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    }

    // Function to assign a task to another user
    function assignTask(task) {
        var taskId = task.id;
        var userId = $("#userId").val();

        if (userId === "") {
            alert("Please enter a user ID");
            return;
        }

        var api_link = `http://localhost:8080/api/tasks/${token}/${taskId}/${userId}`;
        $.ajax({
            url: api_link,
            type: "PUT",
            contentType: "application/json",
            success: function (data) {
                if (data.message != null) {
                    alert(data.message);
                } else {
                    location.reload();
                }
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    }

    // Event listener to filter tasks by completion status
    $("#completionStatus").change(function () {
        var completedStatus = $("#completionStatus").val();
        var link = `http://localhost:8080/api/filter/completionstatus/${token}/${completedStatus}`;

        if (completedStatus === "all") {
            link = `http://localhost:8080/api/search/user/${userId}`;
        }

        $.ajax({
            url: link,
            type: "GET",
            dataType: "json",
            success: function (tasks) {
                if (tasks.message != null) {
                    alert(tasks.message);
                } else {
                    display(tasks);
                }
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    });

    // Event listener to filter tasks by due date
    $("#dueDate").change(function () {
        var dueDate = $("#dueDate").val();

        $.ajax({
            url: `http://localhost:8080/api/filter/duedate/${token}/${dueDate}`,
            type: "GET",
            dataType: "json",
            success: function (tasks) {
                if (tasks.message != null) {
                    alert(tasks.message);
                } else {
                    display(tasks);
                }
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    });

    // Event listener to search tasks by title
    $("#searchButton").click(function () {
        var title = $("#searchTitle").val();

        $.ajax({
            url: `http://localhost:8080/api/search/title/${token}/${title}`,
            type: "GET",
            dataType: "json",
            success: function (tasks) {
                display(tasks);
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    });

    // Event listener to search tasks by description
    $("#searchButton1").click(function () {
        var description = $("#searchDescription").val();

        $.ajax({
            url: `http://localhost:8080/api/search/desc/${token}/${description}`,
            type: "GET",
            dataType: "json",
            success: function (tasks) {
                display(tasks);
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    });

    // Function to edit task
    function editTask(task) {
        var div = $("#hidden");
        div.css("visibility", "visible");
        div.empty();

        var p1 = $("<input>")
            .attr("id", "p1")
            .addClass("form-control mt-4 w-75 mx-auto")
            .val(task.title);
        var p2 = $("<input>")
            .attr("id", "p2")
            .addClass("form-control mt-4 w-75 mx-auto")
            .val(task.description);
        var p3 = $("<input>")
            .attr("id", "p3")
            .attr("type", "date")
            .addClass("form-control mt-4 w-75 mx-auto")
            .val(task.dueDate);
        var btn = $("<button>")
            .text("Update")
            .addClass("btn btn-primary mt-4");
        var btn1 = $("<button>")
            .text("Cancel")
            .addClass("btn btn-danger mt-4 ml-3");

        div.attr("class", "text-center pb-4");
        div.append(p1, p2, p3, btn, btn1);

        btn1.on("click", function () {
            div.css("visibility", "hidden");
        });

        btn.on("click", function () {
            editFun(task);
        });

        function editFun(task) {
            var editedTask = {
                id: task.id,
                title: $("#p1").val(),
                description: $("#p2").val(),
                dueDate: $("#p3").val(),
            };

            $.ajax({
                url: `http://localhost:8080/api/tasks/${token}`,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(editedTask),
                success: function (response) {
                    if (response.message != null) {
                        alert(response.message);
                    } else {
                        alert("Task updated successfully");
                        window.location.href = "index.html";
                    }
                },
                error: function (error) {
                    console.error("Error:", error);
                },
            });
        }
    }

    // Initial fetch of tasks
    var url = `http://localhost:8080/api/search/user/${userId}`;
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: function (data) {
            display(data);
        },
        error: function (error) {
            console.error("Error:", error);
        },
    });

    // Logout function
    $("#logoutButton").click(function () {
        var url = `http://localhost:8080/api/users/logout/${token}`;
        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json",
            success: function () {
                localStorage.removeItem("user");
                window.location.href = "login.html";
            },
            error: function (error) {
                console.error("Error:", error);
            },
        });
    });
});
