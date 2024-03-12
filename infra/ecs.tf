resource "aws_ecs_cluster" "ecs" {
    name = "app_cluster"
}

resource "aws_ecs_task_definition" "td" {
    container_definitions = jsonencode([
        {
            name = "app"
            image = "860605510018.dkr.ecr.sa-east-1.amazonaws.com/app_repo"
            cpu = 256
            memory = 512
            essetials = true
            portMappings = [
                {
                 containerPort = 80
                 hostPort = 80
                }
            ]   
        }
    ])
    family = "app"
    requires_compatibilities = ["FARGATE"]

    cpu = "256"
    memory = "512"
    network_mode = "awsvpc"
    task_role_arn = "arn:aws:iam::860605510018:role/ecsTaskExecutionRole"
    execution_role_arn = "arn:aws:iam::860605510018:role/ecsTaskExecutionRole"
}

resource "aws_ecs_service" "service" {
    name = "app_service"
    cluster = aws_ecs_cluster.ecs.arn
    launch_type = "FARGATE"
    enable_execute_command = true

    deployment_maximum_percent = 200
    deployment_minimum_healthy_percent = 100
    desired_count = 1
    task_definition = aws_ecs_task_definition.td.arn

    network_configuration {
        assign_public_ip = true
        security_groups = ["sg-05c8e1e280f9f6b2e"]
        subnets = ["subnet-00e0921b25f5b381c", "subnet-0ab3010574fcfe6c9", "subnet-01ebe4f621d5d98ff"]
    }
}
