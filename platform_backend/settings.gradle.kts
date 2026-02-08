rootProject.name = "platform_backend"

extra["springCloudVersion"] = "2025.1.0"

include("common_module")
include("gateway")
include("agent_service")
include("testing_engine")
include("testcase_service:testcase_domain")
include("testcase_service:testcase_persistence")
// includeBuild("build-logic")
