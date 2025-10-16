# ☁️ Core to Cloud Java

A hands-on exploration of **Java development from Core principles to Cloud-native microservices**.  
This repository captures structured learning, reference code, and experiments focused on building **production-grade, observable, and scalable** systems using **Java (17/21)**, **Spring Boot**, and **modern DevOps**.

---

## 📘 Objective

Strengthen end-to-end expertise to deliver **high-throughput, low-latency, resilient** services:

- **Core Java & Modern Language Features** — OOP, collections, streams, **records**, **pattern matching**, **switch expressions**, **virtual threads**  
- **Concurrency & Performance** — threads, **CompletableFuture**, locks/atomics, **JVM internals**, **memory management**, **GC tuning**  
- **Spring Boot Microservices** — REST, JPA/Hibernate, **WebFlux**, Security (JWT/RBAC), **Spring Batch**  
- **Cloud & DevOps** — Docker, **Kubernetes**, **Helm**, CI/CD, **observability (OpenTelemetry, Prometheus, Grafana, Dynatrace)**  
- **Architecture & Reliability** — backpressure, circuit breakers, retries, idempotency, trace correlation

This repository demonstrates how foundational Java concepts evolve into **robust, traceable microservices** ready for enterprise environments.

---

## 🧩 Repository Structure

```

core-to-cloud-java/
│
├── core-java/
│   ├── oops/                     # Encapsulation, inheritance, polymorphism, abstraction
│   ├── collections/              # List, Set, Map, Stream operations
│   ├── exceptions/               # Exception handling and custom exceptions
│   ├── generics/                 # Type safety and wildcard usage
│   ├── streams-lambda/           # Java 8+ functional programming
│   ├── language-modern/          # JDK 14+ features:
│   │   ├── records/              # Immutable DTOs; equals/hashCode/toString
│   │   ├── pattern-matching/     # instanceof + switch pattern matching
│   │   └── switch-expressions/   # Expression-style switch
│   ├── concurrency/              # Threads, ExecutorService, CompletableFuture
│   │   ├── executors-completablefuture/
│   │   ├── locks-atomics/        # ReentrantLock, StampedLock, LongAdder
│   │   ├── virtual-threads/      # Loom (JDK 21) thread-per-request demos
│   │   └── structured-concurrency/ # JDK 21 (optional)
│   └── jvm-internals/
│       ├── memory-model/         # stack/heap/metaspace/TLAB/safepoints
│       ├── gc/                   # G1/ZGC/Shenandoah; tuning scenarios & flags
│       └── profiling/            # JFR, jcmd, async-profiler, JMH harness
│
├── spring-boot/
│   ├── rest-api/                 # RESTful services and layered architecture
│   ├── jpa-hibernate/            # ORM mapping, pagination, N+1 fixes
│   ├── webflux/                  # Reactive programming (Mono, Flux, backpressure)
│   ├── security/                 # JWT, RBAC, filter chains
│   ├── batch-processing/         # Spring Batch (reader/processor/writer)
│   └── observability/
│       ├── micrometer-prometheus/   # /actuator/prometheus, custom meters
│       └── opentelemetry-tracing/   # OTel auto-instrumentation & context propagation
│
├── devops/
│   ├── docker/                   # Multi-stage Dockerfiles, health checks
│   ├── kubernetes/
│   │   ├── manifests/            # Deployment/Service/HPA + probes & resources
│   │   └── helm/                 # Chart skeleton: values, templates, annotations
│   └── ci-cd/                    # GitHub Actions / Jenkins pipelines
│
└── notes/
├── theory.md                 # Short summaries & references
├── interview-questions.md    # Curated Q&A and coding drills
├── jvm-cheatsheet.md         # Memory model, classloading, safepoints
├── gc-tuning-playbook.md     # Practical flags & troubleshooting flow
└── observability-playbook.md # Traces, spans, metrics, logs, dashboards

```

---

## 🚀 Roadmap

| Stage | Focus | Description | Status |
|------:|-------|-------------|:-----:|
| 1 | **Core Java Refresher** | OOP, Collections, Generics, Exceptions | ☐ |
| 2 | **Concurrency & Streams** | Threads, **CompletableFuture**, Lambdas | ☐ |
| 3 | **Modern Java** | **Records, pattern matching, switch expressions** | ☐ |
| 4 | **Virtual Threads** | Loom (JDK 21) thread-per-request & benchmarks | ☐ |
| 5 | **JVM & GC** | Memory model, **G1/ZGC**, tuning playbook | ☐ |
| 6 | **Spring Boot Essentials** | Controllers, Services, REST APIs | ☐ |
| 7 | **JPA & Hibernate** | CRUD, relationships, performance | ☐ |
| 8 | **WebFlux & Reactive** | Non-blocking APIs, backpressure | ☐ |
| 9 | **Security** | JWT, RBAC, filters | ☐ |
| 10 | **Spring Batch** | ETL-style pipelines | ☐ |
| 11 | **Observability** | **OpenTelemetry**, Micrometer/Prometheus, Grafana | ☐ |
| 12 | **Cloud & DevOps** | Docker, **K8s**, **Helm**, CI/CD | ☐ |
| 13 | **Microservice Design** | Resilience (retries, circuit breakers, idempotency) | ☐ |

*(Mark progress using `[x]` as you complete each stage.)*

---

## 🧪 Practical Drills (what goes into code folders)

- **Fan-out/Fan-in Aggregator** — `CompletableFuture.allOf()` and **virtual thread** variant; compare throughput/latency.  
- **Contention Lab** — `ReentrantLock` vs `StampedLock`, `AtomicLong` vs **`LongAdder`**, false sharing note.  
- **Backpressure Demo** — WebFlux SSE/stream with `onBackpressureBuffer()` and bounded queues.  
- **Idempotency & Retries** — Rest client with jittered backoff + idempotency keys.  
- **Trace Propagation** — Two services with **OTel** headers; verify **traceId/spanId** continuity.  
- **GC Tuning Case** — Same workload with **G1** vs **ZGC**; capture JFR and highlight pause times.

---

## ⚙️ Tools & Environment

- **Java:** 21 (LTS) preferred; 17 supported  
- **Spring Boot:** 3.x  
- **Build:** Maven / Gradle  
- **DB:** PostgreSQL / H2  
- **Containerization:** Docker  
- **Orchestration:** Kubernetes, **Helm** (parameterized resources, probes, annotations)  
- **Observability:** Spring Actuator, **Micrometer + Prometheus**, **OpenTelemetry** (export OTLP), Grafana; Dynatrace (agent/OTLP)  
- **CI/CD:** GitHub Actions / Jenkins

---

## 🔍 JVM & GC Quick Reference

**Common flags (G1):**
```

-XX:+UseG1GC -Xms2g -Xmx2g
-XX:MaxGCPauseMillis=200
-XX:+ParallelRefProcEnabled -XX:+AlwaysPreTouch
-XX:+UseStringDeduplication
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dumps

```

**Low-pause (ZGC/Shenandoah):**
```

-XX:+UseZGC -Xms2g -Xmx2g

```

**Profiling:**
- **JFR**: `jcmd <pid> JFR.start` / `jfr print`  
- **jcmd**: GC/run, heap summary, thread dump  
- **async-profiler**: CPU/alloc flamegraphs (documented in `profiling/`)

---

## 🛰️ Tracing & Metrics (Micrometer / OpenTelemetry)

- **Traces**: Every request carries a **traceId**; each operation creates a **span** with attributes/events; propagate context across service boundaries.  
- **Metrics**: Use Micrometer (`@Timed`, counters, gauges). Scrape via **/actuator/prometheus**.  
- **Dashboards**: Grafana panels for latency/throughput/error rates; link logs by **traceId**.  
- **Dynatrace**: Either OTLP export or Dynatrace Java agent as needed.

---

## 🏁 Author

**Abhishek Prakash Sharma**  
Senior Software Developer | Cloud & AI Enthusiast  
