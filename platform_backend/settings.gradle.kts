rootProject.name = "platform_backend"

// ── Shared modules ──
include("shared-domain")
include("shared-event")
include("shared-infrastructure")

// ── Services ──
include("gateway-service")
include("admin-service")
include("engine-service")
include("report-service")
