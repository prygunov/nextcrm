package net.artux.nextcrm.controller.orders;

import net.artux.nextcrm.controller.util.BaseController;
import net.artux.nextcrm.model.Analytics;
import net.artux.nextcrm.repository.AppealsRepository;
import net.artux.nextcrm.repository.orders.AverageAndCount;
import net.artux.nextcrm.repository.orders.OrdersRepository;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController extends BaseController {

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final AppealsRepository appealsRepository;

    public AnalyticsController(OrdersRepository ordersRepository, UsersRepository usersRepository, AppealsRepository appealsRepository) {
        super("Аналитика");
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
        this.appealsRepository = appealsRepository;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PostMapping
    public Object getAnalytics(@RequestParam Date startPeriod, @RequestParam Date endPeriod, Model model){
        Analytics analytics =ordersRepository.getAnalytics(startPeriod, endPeriod);
        AverageAndCount averageAndCount = ordersRepository.getAverage(startPeriod, endPeriod);
        analytics.setAverage(averageAndCount.getAvg());
        analytics.setCount(averageAndCount.getCount());

        model.addAttribute("object", analytics);
        model.addAttribute("users", usersRepository.getUserAnalytics(startPeriod, endPeriod));
        model.addAttribute("channels", appealsRepository.getChannelAnalytics(startPeriod, endPeriod));
        model.addAttribute("startPeriod", startPeriod);
        model.addAttribute("endPeriod", endPeriod);

        return pageWithContent("analytics/view", model);
    }

    @Override
    protected Object getHome(Model model) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date start = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = calendar.getTime();
        return getAnalytics(start, end, model);
    }
}
