# Task-management-system

The Task Management System is a robust application designed to streamline task and user management. It offers a comprehensive set of features, including user registration, task creation and updates, task assignment, completion tracking, task deletion, and powerful search and filtering capabilities.

# Features
  # User Management
  
        User Registration: Seamlessly register an account by providing essential user information.
        User Login: Securely access your account using your login credentials.
        User Logout: Log out securely to protect your account.
        
  # Task Management
  
        Create Task: Effortlessly create new tasks by providing task-specific details.
        Update Task: Modify existing task details with ease.
        Assign Task to Another User: Delegate tasks to other users as needed.
        Mark Task as Complete: Keep track of task progress by marking tasks as complete.
        Delete Task: Remove tasks when they are no longer relevant.
        
  # Search and Filter
       
        Search Task by Title: Quickly locate tasks by searching for specific titles.
        Search Task by Description: Find tasks based on their descriptions.
        Search Tasks of a User: Retrieve all tasks associated with a particular user.
        Filter Tasks by Completion Status: Efficiently filter tasks based on their completion status.
        Filter Tasks by Due Date: Organize tasks by filtering them according to their due dates.
        Filter Tasks by Completion Status and Due Date: Combine filters for precise task selection.
        
# Technologies Used
        
        Java: A versatile programming language for robust backend development.
        Spring Boot: A powerful framework for building efficient and scalable applications.
        Hibernate: A reliable object-relational mapping (ORM) framework.
        Maven: A widely used build automation tool for managing project dependencies.
        MySQL: A reliable relational database management system.
        HTML, CSS, JavaScript, and Bootstrap: Technologies for crafting a user-friendly front-end interface.
        
# API Endpoints

  # User Endpoints
       
        POST /api/users: Register a new user.
        POST /api/users/login: Login to a user account.
        POST /api/users/logout/{token}: Log out from a user account.
        
  # Task Endpoints
     
        POST /api/tasks/{token}: Create a new task.
        PUT /api/tasks/{token}: Update an existing task.
        PUT /api/tasks/{token}/{taskId}/{userId}: Assign a task to another user.
        PUT /api/tasks/complete/{token}/{taskId}: Mark a task as complete.
        DELETE /api/tasks/{token}/{taskId}: Delete a task.
        
  # Search and Filter Endpoints
      
        GET /api/search/title/{token}/{title}: Search for tasks by title.
        GET /api/search/desc/{token}/{desc}: Search for tasks by description.
        GET /api/search/user/{userId}: Retrieve tasks of a specific user.
        GET /api/filter/completionstatus/{token}/{completedStatus}: Filter tasks by completion status.
        GET /api/filter/duedate/{token}/{dueDate}: Filter tasks by due date.
        GET /api/filter/{token}/{completedStatus}/{dueDate}: Filter tasks by completion status and due date.
        
# Getting Started
      
        Clone the Repository: Begin by cloning this repository to your local machine.
        Build the Project: Execute mvn clean install to build the project.
        Database Configuration: Configure the database settings in application.properties.
        Run the Application: Start the application with mvn spring-boot:run.
        Access the Application: Open your web browser and visit http://localhost:8080 to access the application.
