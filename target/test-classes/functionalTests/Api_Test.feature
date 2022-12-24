Feature: Pruebas de api testing

  Scenario Outline: lista de usuarios
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion List get
    Then el status de la respuesta debe ser <status>

    Examples: Se realiza el listado de usuarios
      | endpoint          | status |
      | /api/users?page=2 | 200    |


  Scenario Outline: lista de recursos
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion List get
    Then el status de la respuesta debe ser <status>

    Examples: Se realiza el listado de usuarios
      | endpoint     | status |
      | /api/unknown | 200    |


  Scenario Outline: create
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion post con "<name>" y "<job>"
    Then se visualiza la respuesta con los datos "<name>" y "<job>"
    Then el status de la respuesta debe ser <status>

    Examples: Se crean los usuarios
      | endpoint     | name | job    | status |
      | /api/users | Lis  | leader | 201    |


  Scenario Outline: update de usuarios
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion put con "<name>" y "<job>"
    Then se visualiza la respuesta con los datos "<name>" y "<job>"
    Then el status de la respuesta debe ser <status>

    Examples: Se actualizan usuarios
      | endpoint     | name  | job    | status |
      | /api/users/2 | Frank | leader | 200    |

  Scenario Outline: Update con patch
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion patch con "<name>" y "<job>"
    Then se visualiza la respuesta con los datos "<name>" y "<job>"
    Then el status de la respuesta debe ser <status>

    Examples: Se actualiza con patch
      | endpoint     | name  | job    | status |
      | /api/users/2 | Ander | leader | 200    |

  Scenario Outline: Se elimina usuarios
    Given Se tiene el endpoint "<endpoint>"
    When se realiza la peticion delete
    Then el status de la respuesta debe ser <status>

    Examples: Se relimina usuarios
      | endpoint          | status |
      | /api/users?page=2 | 204    |

  Scenario Outline: Registro satisfactorio
    Given Se tiene el endpoint "<endpoint>"
    When se realiza el registro con "<email>" y "<pass>"
    Then el status de la respuesta debe ser <status>

    Examples: Se rregistran usuarios
      | endpoint      | email              | pass   | status |
      | /api/register | eve.holt@reqres.in | pistol | 200    |

  Scenario Outline: Registro unhappy
    Given Se tiene el endpoint "<endpoint>"
    When se realiza el registro con "<email>" y "<pass>"
    Then Se visualiza el error "<error>"
    Then el status de la respuesta debe ser <status>

    Examples: Unhappy sin pass
      | endpoint      | email              | pass | error            | status |
      | /api/register | eve.holt@reqres.in |      | Missing password | 400    |

  Scenario Outline: Login satisfactorio
    Given Se tiene el endpoint "<endpoint>"
    When se realiza el login con "<email>" y "<pass>"
    Then el status de la respuesta debe ser <status>

    Examples: Se realiza login de usuarios
      | endpoint   | email              | pass   | status |
      | /api/login | eve.holt@reqres.in | pistol | 200    |

  Scenario Outline: Login unhappy
    Given Se tiene el endpoint "<endpoint>"
    When se realiza el login con "<email>" y "<pass>"
    Then Se visualiza el error "<error>"
    Then el status de la respuesta debe ser <status>

    Examples: Unhappy sin pass
      | endpoint   | email              | pass | error            | status |
      | /api/login | eve.holt@reqres.in |      | Missing password | 400    |
