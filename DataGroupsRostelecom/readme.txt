путь к файлу students.csv указан статично в классе Servlet

код, при помощи котрого сгенерирована документация на SwaggerHub:
openapi: 3.0.0
info:
  title: Журнал успеваемости учеников OpenAI спецификация
  version: '1.1'
servers: 
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: https://virtserver.swaggerhub.com/FozelRockfire/RTK/1.1
  - url: http://localhost:8080/v1/
paths:
  /persons/update-grade/{firstname}/{lastname}/{subject}/{newGrade}/{groupNumber}:
    put:
      summary: Изменить оценку ученика по ФИО, предмету и номеру группы
      parameters:
        - name: firstname
          in: path
          required: true
          description: Имя ученика
          schema:
            type: string
            example: Дмитрий
        - name: lastname
          in: path
          required: true
          description: Фамилия ученика
          schema:
            type: string
            example: Абрамов
        - name: subject
          in: path
          required: true
          description: Название предмета
          schema:
            type: string
            example: rus
        - name: newGrade
          in: path
          required: true
          description: Новая оценка
          schema:
            type: string
            example: "5"
        - name: groupNumber
          in: path
          required: true
          description: Номер группы
          schema:
            type: string
            example: "11"
      responses:
        '200':
          description: Успешное обновление оценки
        '404':
          description: Ученик не найден
        '400':
          description: Некорректный запрос

  /persons/average-grade/{group}:
    get:
      summary: Метод получения средних оценок студентов в указанной группе
      parameters:
        - name: group
          in: path
          required: true
          description: Номер группы
          schema:
            type: string
      responses:
        '200':
          description: Успешный запрос
          content:
            application/json:
              example:
                students:
                  - firstname: "Дмитрий"
                    lastname: "Абрамов"
                    averageGrade: 4.75
                    groupNumber: 11
                  - firstname: "Иван"
                    lastname: "Иванов"
                    averageGrade: 4.75
                    groupNumber: 8
        '404':
          description: Ученик не найден
        '400':
          description: Некорректный запрос