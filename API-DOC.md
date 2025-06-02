# 📘 OpenAPI definition

---

## 📚 Endpoints

### `POST` /api/v1/chats/{chatId}/users/{userId}/send-message
**Operation:** `sendChatMessage`  
**Tags:** chat-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| userId | path | integer | True |
| chatId | path | integer | True |

#### 📦 Request Body
**Content Type:** `application/json`
- Schema: `ChatMessageCreateRequest`
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `ChatMessageViewDTO` |

---
### `POST` /api/v1/chats/{chatId}/users/{userId}/reply-message/{messageId}
**Operation:** `replyChatMessage`  
**Tags:** chat-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| chatId | path | integer | True |
| userId | path | integer | True |
| messageId | path | integer | True |

#### 📦 Request Body
**Content Type:** `application/json`
- Schema: `ChatMessageCreateRequest`
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `ChatMessageViewDTO` |

---
### `POST` /api/v1/chats/new
**Operation:** `createChat`  
**Tags:** chat-controller

#### 🧾 Parameters
_None_
#### 📦 Request Body
**Content Type:** `application/json`
- Schema: `ChatCreateRequest`
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `ChatViewDTO` |

---
### `POST` /api/v1/auth/sign-up
**Operation:** `signIn`  
**Tags:** auth-controller

#### 🧾 Parameters
_None_
#### 📦 Request Body
**Content Type:** `application/json`
- Schema: `UserSignUpRequest`
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `UserViewDTO` |

---
### `POST` /api/v1/auth/log-in
**Operation:** `logIn`  
**Tags:** auth-controller

#### 🧾 Parameters
_None_
#### 📦 Request Body
**Content Type:** `application/json`
- Schema: `UserSignInRequest`
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `UserViewDTO` |

---
### `GET` /api/v1/users/{userId}/chats
**Operation:** `getUserChats`  
**Tags:** user-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| userId | path | integer | True |

#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `array` |

---
### `GET` /api/v1/users/{id}
**Operation:** `getUserById`  
**Tags:** user-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| id | path | integer | True |

#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `UserViewDTO` |

---
### `GET` /api/v1/users/all
**Operation:** `getAllUsers`  
**Tags:** user-controller

#### 🧾 Parameters
_None_
#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `array` |

---
### `GET` /api/v1/chats/{id}
**Operation:** `getChatById`  
**Tags:** chat-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| id | path | integer | True |

#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `ChatViewDTO` |

---
### `GET` /api/v1/chats/{chatId}/messages
**Operation:** `getChatMessages`  
**Tags:** chat-controller

#### 🧾 Parameters
| Name | In | Type | Required |
|------|----|------|----------|
| chatId | path | integer | True |

#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `array` |

---
### `GET` /api/v1/chats/all
**Operation:** `getAllChats`  
**Tags:** chat-controller

#### 🧾 Parameters
_None_
#### 📦 Request Body
_None_
#### 📤 Responses
| Code | Description | Schema |
|------|-------------|--------|
| 200 | OK | `array` |

---

## 📦 Schemas

### `ChatMessageCreateRequest`
| Field | Type |
|-------|------|
| message | string |

### `ChatMessageViewDTO`
| Field | Type |
|-------|------|
| id | integer |
| chatName | string |
| senderName | string |
| chatMessage | string |
| replies | object |
| timestamp | string |

### `ChatCreateRequest`
| Field | Type |
|-------|------|
| creatorId | integer |
| chatName | string |
| participants | array |

### `ChatViewDTO`
| Field | Type |
|-------|------|
| id | integer |
| chatName | string |
| createdAt | string |

### `UserSignUpRequest`
| Field | Type |
|-------|------|
| username | string |
| email | string |
| password | string |
| confirmPassword | string |

### `UserViewDTO`
| Field | Type |
|-------|------|
| username | string |
| email | string |

### `UserSignInRequest`
| Field | Type |
|-------|------|
| usernameOrEmail | string |
| password | string |
| email | boolean |
